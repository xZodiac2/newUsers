package com.ilya.loginandregistration.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ilya.core.model.User
import com.ilya.data.UsersRepository
import com.ilya.loginandregistration.common.Error
import com.ilya.loginandregistration.common.extract
import com.ilya.loginandregistration.databinding.FragmentLoginBinding
import com.ilya.loginandregistration.login.navigation.LoginFragmentRouter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {
    
    @Inject lateinit var loginFragmentRouter: LoginFragmentRouter
    @Inject lateinit var usersRepository: UsersRepository
    
    private lateinit var binding: FragmentLoginBinding
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.btnLogin.setOnClickListener(this::login)
        binding.btnOfferToRegister.setOnClickListener { loginFragmentRouter.goToRegistration() }
    }
    
    private fun login(view: View) = with(binding) {
        authenticate()
            .onFailure { error ->
                tvError.text = (error as Error).extract(requireContext())
                etPasswordInput.setText("")
                etLoginInput.setText("")
            }
            .onSuccess { user ->
                loginFragmentRouter.goToGreeting(user.id)
            }
    }
    
    private fun authenticate(): Result<User> = with(binding) {
        val login = etLoginInput.text.toString()
        val password = etPasswordInput.text.toString()
        
        return when(val foundUser = usersRepository.getUserByLoginAndPassword(login, password)) {
            null -> Result.failure(Error.LoginError.WrongLoginOrPasswordError)
            else -> Result.success(foundUser)
        }
    }
    
}
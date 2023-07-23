package com.ilya.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ilya.data.users.UsersRepository
import com.ilya.core.exceptions.UserNotFoundException
import com.ilya.domain.models.UserLoginParams
import com.ilya.domain.usecases.FindUserByLoginAndPasswordUseCase
import com.ilya.presentation.R
import com.ilya.presentation.databinding.FragmentLoginBinding
import com.ilya.presentation.login.navigation.LoginFragmentRouter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {
    
    @Inject lateinit var loginFragmentRouter: LoginFragmentRouter
    @Inject lateinit var usersRepository: UsersRepository
    
    private lateinit var binding: FragmentLoginBinding
    private lateinit var findUserByLoginAndPasswordUseCase: FindUserByLoginAndPasswordUseCase
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.btnLogin.setOnClickListener(this::login)
    }
    
    private fun login(view: View) = with(binding) {
        val login = etLoginInput.text.toString()
        val password = etPasswordInput.text.toString()
        
        findUserByLoginAndPasswordUseCase = FindUserByLoginAndPasswordUseCase(usersRepository, UserLoginParams(login, password))
        
        try {
            val user = findUserByLoginAndPasswordUseCase.execute()
            loginFragmentRouter.goToGreeting(user.id)
        } catch (userNotFoundEx: UserNotFoundException) {
            tvError.text = getString(R.string.text_error_invalid_input)
        }
    }
    
}
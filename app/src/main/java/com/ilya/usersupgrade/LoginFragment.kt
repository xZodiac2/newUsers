package com.ilya.usersupgrade

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import com.ilya.usersupgrade.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    
    private lateinit var binding: FragmentLoginBinding
    private lateinit var navController: NavController
    private lateinit var usersRepository: UsersRepository
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        
        btnOfferToRegister.setOnClickListener { navController.navigate(LoginFragmentDirections.actionLoginFragmentToRegistrationFragment()) }
        btnLogin.setOnClickListener(this@LoginFragment::login)
    }
    
    override fun onStart() {
        super.onStart()
        (requireActivity().applicationContext as UsersApplication).apply {
            this@LoginFragment.navController = navController
            this@LoginFragment.usersRepository = usersRepository
        }
    }
    
    private fun login(view: View) {
        authenticate()
            .onSuccess { giveAccess(it) }
            .onFailure { error ->
                activity
                binding.tvError.visibility = View.VISIBLE
                binding.tvError.text = (error as Error).extract(requireActivity())
                clearInputFields()
            }
    }
    
    private fun authenticate(): Result<User> = with(binding) {
        val userLogin = etLoginInput.text.toString()
        val userPassword = etPasswordInput.text.toString()
        
        return when (val foundUser = usersRepository.findUserByLoginAndPassword(userLogin, userPassword)) {
            null -> Result.failure(Error.WrongLoginOrPasswordError)
            else -> Result.success(foundUser)
        }
    }
    
    private fun giveAccess(user: User) {
        val actionToUserGreeting = LoginFragmentDirections.actionLoginFragmentToUserGreetingFragment(user.userId)
        navController.navigate(actionToUserGreeting)
    }
    
    private fun clearInputFields() = with(binding) {
        etLoginInput.setText("")
        etPasswordInput.setText("")
    }
    
}
package com.ilya.loginandregistration.login.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ilya.loginandregistration.databinding.FragmentLoginBinding
import com.ilya.loginandregistration.login.domain.models.LoginParams
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : Fragment() {
    
    private lateinit var binding: FragmentLoginBinding
    
    private val loginViewModel: LoginViewModel by viewModels()
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        
        btnLogin.setOnClickListener(::login)
        btnOfferToRegister.setOnClickListener { loginViewModel.goToRegistration() }
        binding.lifecycleOwner = this@LoginFragment
        binding.viewModel = loginViewModel
        
    }
    
    private fun login(view: View) = with(binding) {
        val login = etLoginInput.text.toString()
        val password = etPasswordInput.text.toString()
        loginViewModel.login(LoginParams(login, password), requireContext())
    }
    
}
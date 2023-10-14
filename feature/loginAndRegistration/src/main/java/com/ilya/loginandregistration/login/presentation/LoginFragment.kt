package com.ilya.loginandregistration.login.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ilya.loginandregistration.databinding.FragmentLoginBinding
import com.ilya.loginandregistration.login.presentation.navigation.LoginFragmentRouter
import com.ilya.loginandregistration.login.presentation.view.LoginView
import com.ilya.loginandregistration.login.presentation.viewModel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {
    
    private lateinit var binding: FragmentLoginBinding
    private lateinit var view: LoginView
    
    private val loginViewModel: LoginViewModel by viewModels()
    
    @Inject
    lateinit var loginFragmentRouter: LoginFragmentRouter
    
    override fun onAttach(context: Context) {
        super.onAttach(context)
        
        loginViewModel.loginFragmentRouter = loginFragmentRouter
    }
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        view = LoginView(binding, loginViewModel)
        lifecycleScope.launchWhenStarted { loginViewModel.screenStateFlow.collect(view::bind) }
        return binding.root
    }
    
}
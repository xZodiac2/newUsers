package com.ilya.loginandregistration.login.presentation.view

import com.ilya.core.computedMD5Hash
import com.ilya.core.setTextByReference
import com.ilya.core.setViewVisibility
import com.ilya.loginandregistration.databinding.FragmentLoginBinding
import com.ilya.loginandregistration.login.domain.models.UserLoginParams
import com.ilya.loginandregistration.login.presentation.callback.LoginViewCallback
import com.ilya.loginandregistration.login.presentation.state.LoginViewState
import hilt_aggregated_deps._com_ilya_loginandregistration_login_presentation_LoginFragment_GeneratedInjector
import kotlin.math.log

class LoginView(
    private val binding: FragmentLoginBinding,
    private val callback: LoginViewCallback
) {
    
    init {
        initViews()
    }
    
    private fun initViews() = with(binding) {
        btnLogin.setOnClickListener { callback.onLoginClick(UserLoginParams(etLogin.text.toString(), etPassword.text.toString().computedMD5Hash())) }
        btnOfferToRegister.setOnClickListener { callback.onOfferToRegisterClick() }
    }
    
    fun bind(loginViewState: LoginViewState?) = with(binding) {
        loginViewState ?: return
        
        tvError.setTextByReference(loginViewState.loginError?.textReference)
        
        progressBar.setViewVisibility(loginViewState.progressBarVisibility)
        btnLogin.setViewVisibility(loginViewState.buttonVisibility)
    }
    
}
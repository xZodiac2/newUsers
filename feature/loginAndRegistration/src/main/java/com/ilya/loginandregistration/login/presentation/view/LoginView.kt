package com.ilya.loginandregistration.login.presentation.view

import androidx.core.widget.doOnTextChanged
import com.ilya.core.computedMD5Hash
import com.ilya.core.setTextByReference
import com.ilya.core.setViewVisibility
import com.ilya.loginandregistration.databinding.FragmentLoginBinding
import com.ilya.loginandregistration.login.domain.models.UserLoginParams
import com.ilya.loginandregistration.login.presentation.callback.LoginViewCallback
import com.ilya.loginandregistration.login.presentation.state.LoginViewState

class LoginView(
    private val binding: FragmentLoginBinding,
    private val callback: LoginViewCallback,
) {
    
    init {
        initViews()
    }
    
    private fun initViews() = with(binding) {
        btnLogin.setOnClickListener {
            callback.onLoginClick(
                UserLoginParams(
                    etLogin.text.toString(),
                    etPassword.text.toString().computedMD5Hash()
                )
            )
        }
        btnOfferToRegister.setOnClickListener { callback.onOfferToRegisterClick() }
        
        etLogin.doOnTextChanged { _, _, _, _ -> callback.onInputFieldsChanged() }
        etPassword.doOnTextChanged { _, _, _, _ -> callback.onInputFieldsChanged() }
    }
    
    fun bind(loginViewState: LoginViewState?) = with(binding) {
        loginViewState ?: return
        
        tvError.setTextByReference(loginViewState.loginError?.textReference)
        
        progressBar.setViewVisibility(loginViewState.progressBarVisibility)
        btnLogin.setViewVisibility(loginViewState.buttonVisibility)
    }
    
}
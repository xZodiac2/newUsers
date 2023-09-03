package com.ilya.loginandregistration.login.presentation.callback

import com.ilya.loginandregistration.login.domain.models.UserLoginParams

interface LoginViewCallback {
    fun onLoginClick(loginParams: UserLoginParams)
    fun onOfferToRegisterClick()
}
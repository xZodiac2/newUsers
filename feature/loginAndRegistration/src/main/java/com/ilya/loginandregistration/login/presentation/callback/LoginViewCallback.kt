package com.ilya.loginandregistration.login.presentation.callback

import com.ilya.loginandregistration.login.domain.models.UserLoginParams
import kotlinx.coroutines.Job

interface LoginViewCallback {
    fun onLoginClick(loginParams: UserLoginParams): Job
    fun onOfferToRegisterClick()
}
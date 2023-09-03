package com.ilya.loginandregistration.login.presentation.state

import com.ilya.loginandregistration.login.presentation.error.LoginPresentationError

data class LoginViewState(
    val loginError: LoginPresentationError?
)
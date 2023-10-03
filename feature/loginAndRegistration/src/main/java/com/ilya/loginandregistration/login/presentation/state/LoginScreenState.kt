package com.ilya.loginandregistration.login.presentation.state

import com.ilya.core.enums.ViewVisibility
import com.ilya.loginandregistration.login.presentation.error.LoginPresentationError

data class LoginScreenState(
    val loginError: LoginPresentationError? = null,
    val buttonVisibility: ViewVisibility = ViewVisibility.VISIBLE,
    val progressBarVisibility: ViewVisibility = ViewVisibility.GONE,
    val errorVisibility: ViewVisibility = ViewVisibility.GONE,
)
package com.ilya.loginandregistration.login.presentation.error

import androidx.annotation.StringRes
import com.ilya.core.abstractClasses.UsersApplicationError
import com.ilya.loginandregistration.R

sealed class LoginError(@StringRes override val stringId: Int) : UsersApplicationError(stringId) {
    object WrongLoginOrPasswordError : LoginError(R.string.text_error_invalid_input)
}
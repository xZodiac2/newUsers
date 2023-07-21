package com.ilya.loginandregistration.common

import androidx.annotation.StringRes
import com.ilya.loginandregistration.R

internal sealed class Error(
    @StringRes val stringId: Int
) : Throwable() {
    
    sealed class Registration(@StringRes stringId: Int) : Error(stringId) {
        object PasswordsDoNotMatch : Registration(R.string.text_registration_passwords_do_not_match_error)
        object PasswordLength : Registration(R.string.text_registration_password_is_small_error)
        object EmptyField : Registration(R.string.text_registration_empty_field_error)
    }

    sealed class Login(@StringRes stringId: Int) : Error(stringId) {
        object WrongLoginOrPassword : Login(R.string.text_error_invalid_input)
    }
    
}

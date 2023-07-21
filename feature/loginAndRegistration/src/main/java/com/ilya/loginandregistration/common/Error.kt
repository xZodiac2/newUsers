package com.ilya.loginandregistration.common

import androidx.annotation.StringRes
import com.ilya.loginandregistration.R

internal sealed class Error(
    @StringRes val stringId: Int
) : Throwable() {
    
    sealed class RegistrationError(@StringRes stringId: Int) : Error(stringId) {
        object PasswordsDoNotMatchError : RegistrationError(R.string.text_registration_passwords_do_not_match_error)
        object PasswordLengthError : RegistrationError(R.string.text_registration_password_is_small_error)
        object EmptyFieldError : RegistrationError(R.string.text_registration_empty_field_error)
    }

    sealed class LoginError(@StringRes stringId: Int) : Error(stringId) {
        object WrongLoginOrPasswordError : LoginError(R.string.text_error_invalid_input)
    }
    
}

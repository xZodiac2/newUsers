package com.ilya.usersupgrade


sealed class Error(val stringId: Int) : Throwable() {
    object InvalidInputError : Error(R.string.text_error_invalid_input)
    
    sealed class InputError(stringId: Int) : Error(stringId) {
        object PasswordsDoNotMatchError : InputError(R.string.text_registration_passwords_do_not_match_error)
        object PasswordIsShort : InputError(R.string.text_registration_password_is_short)
        object LengthError : InputError(R.string.text_registration_input_length_error)
        object EmptyFieldError : InputError(R.string.text_registration_empty_field_error)
        
    }
}
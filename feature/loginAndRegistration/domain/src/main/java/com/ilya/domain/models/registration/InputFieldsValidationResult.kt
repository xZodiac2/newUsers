package com.ilya.domain.models.registration

import com.ilya.domain.error.RegistrationError

data class InputFieldsValidationResult(
    val nameError: RegistrationError?,
    val loginError: RegistrationError?,
    val passwordError: RegistrationError?,
    val repeatedPasswordError: RegistrationError?
)
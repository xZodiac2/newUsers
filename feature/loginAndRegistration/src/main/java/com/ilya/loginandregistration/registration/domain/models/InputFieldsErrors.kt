package com.ilya.loginandregistration.registration.domain.models

import com.ilya.loginandregistration.registration.domain.error.RegistrationError

data class InputFieldsErrors(
    val name: RegistrationError? = null,
    val login: RegistrationError? = null,
    val password: RegistrationError? = null,
    val repeatedPassword: RegistrationError? = null
)
package com.ilya.loginandregistration.registration.domain.models

data class InputFieldsValues(
    val name: String,
    val login: String,
    val password: String,
    val repeatedPassword: String
)

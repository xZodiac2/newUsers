package com.ilya.loginandregistration.registration.presentation.models

data class InputFieldValues(
    val name: String,
    val login: String,
    val password: String,
    val repeatedPassword: String
)

package com.ilya.domain.models.registration

data class RegisteringUserParams(
    val name: String,
    val login: String,
    val password: String,
    val repeatedPassword: String
)
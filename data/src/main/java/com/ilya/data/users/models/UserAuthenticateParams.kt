package com.ilya.data.users.models

data class UserAuthenticateParams(
    val login: String,
    val password: String
)
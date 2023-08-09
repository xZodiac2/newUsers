package com.ilya.loginandregistration.login.domain.models

data class UserLoginParams(
    val login: String,
    val passwordHash: String
)
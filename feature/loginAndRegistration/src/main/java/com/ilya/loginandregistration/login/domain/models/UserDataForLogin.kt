package com.ilya.loginandregistration.login.domain.models

data class UserDataForLogin(
    val login: String,
    val password: String,
    val id: Int
)
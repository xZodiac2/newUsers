package com.ilya.loginandregistration.registration.domain.models

data class NewUserData(
    val name: String,
    val login: String,
    val passwordHash: String
)
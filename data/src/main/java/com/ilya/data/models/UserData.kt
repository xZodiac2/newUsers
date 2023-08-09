package com.ilya.data.models

data class UserData(
    val name: String,
    val login: String,
    val passwordHash: String
)
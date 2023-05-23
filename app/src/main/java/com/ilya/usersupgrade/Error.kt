package com.ilya.usersupgrade

sealed class Error (
    override val message: String
) : Throwable(message) {
    object InvalidInputError : Error("Invalid login or password")
    object UserNameIsNull : Error("Invalid meaning of userName property: null")
}
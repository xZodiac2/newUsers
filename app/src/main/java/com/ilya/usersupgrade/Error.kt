package com.ilya.usersupgrade

sealed class Error (
    override val message: String
) : Throwable(message) {
    object InvalidInputError : Error("Invalid login or password")
    object UserNameIsUndefined : Error("Invalid meaning of userName property: null")
}
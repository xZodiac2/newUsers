package com.ilya.usersupgrade

class User(
    val login: String,
    val password: String,
    val name: String
) {
    var haveAccess = false
}
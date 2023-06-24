package com.ilya.usersupgrade

data class User(
    val name: String,
    val login: String,
    val password: String
) {
    
    val userId = id++
    
    companion object {
        private var id: Int = 0
    }
    
}
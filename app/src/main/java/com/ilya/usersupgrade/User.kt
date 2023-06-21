package com.ilya.usersupgrade

data class User(
    val name: String,
    val login: String,
    val password: String
) {
    
    val userId: Int = id++
    
    companion object {
        private var id: Int = 0
    }
    
}
package com.ilya.core.model

data class User(
    val name: String,
    val login: String,
    val password: String
) {
    
    val id: Int = nextUserId++
    
    companion object {
        private var nextUserId = 0
    }
    
}
package com.ilya.greeting.domain.models

data class User(
    val name: String,
    val login: String,
    val password: String
) {
    val id: Int = userId++
    
    companion object {
        private var userId = 0
    }
}
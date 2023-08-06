package com.ilya.loginandregistration.registration.domain.models

data class User(
    val name: String,
    val login: String,
    val password: String,
) {
    val id: Int = userId++
    
    companion object {
        private var userId = 0
    }
}
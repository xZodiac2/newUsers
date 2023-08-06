package com.ilya.data.users.models

data class UserData(
    val name: String,
    val login: String,
    val password: String,
) {
    val id: Int = userId++
    
    companion object {
        private var userId = 0
    }
}


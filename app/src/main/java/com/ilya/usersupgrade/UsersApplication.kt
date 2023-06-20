package com.ilya.usersupgrade

import android.app.Application

class UsersApplication : Application() {
    
    private val users = mutableListOf(
        User("Ilya", "tomat228", "123"),
        User("SSergey", "Zodiac", "bombom"),
        User("Andrey", "bobik", "kotbegemot")
    )
    
    fun findUserById(id: Int?) : User? {
        return users.find {it.userId == id}
    }
    
    fun findUserByLoginAndPassword(login: String, password: String): User? {
        return users.find {it.login == login && it.password == password}
    }
    
    fun deleteUserById(id: Int) {
        val deletingUser = users.find {it.userId == id}
        
        users.remove(deletingUser)
    }
    
    fun addNewUser(user: User) {
        users += user
    }
    
}
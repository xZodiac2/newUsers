package com.ilya.usersupgrade

import android.app.Application

class MyApplication : Application() {
    
    val users = mutableListOf(
        User("tomat228", "123", "Ilya", 0),
        User("Zodiac", "bombom", "Sergey", 1),
        User("bobik", "kotbegemot", "Andrey", 2)
    )
    
    fun findUserById(id: Int?) : User? {
        return users.find {it.id == id}
    }
    
    fun findUserByLoginAndPassword(login: String, password: String): User? {
        return users.find {it.login == login && it.password == password}
    }
    
    fun deleteUserById(id: Int) {
        val deletingUser = users.find {it.id == id}
        
        users.remove(deletingUser)
    }
    
    fun addNewUser(user: User) {
        users += user
    }
    
}
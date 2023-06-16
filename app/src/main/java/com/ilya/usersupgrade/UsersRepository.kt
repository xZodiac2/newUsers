package com.ilya.usersupgrade

import android.app.Application

class UsersRepository : Application() {
    
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
    
    fun addNewUser(login: String, password: String, name: String) {
        users += User(login, password, name, users[users.size - 1].id + 1)
    }
    
}
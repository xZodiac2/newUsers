package com.ilya.data

import com.ilya.core.model.User
import com.ilya.data.storage.UsersStorage
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersRepository @Inject constructor() {
    
    private val usersStorage = UsersStorage()
    
    fun getUserByLoginAndPassword(login: String, password: String): User? {
        return usersStorage.users.find { user -> user.login == login && user.password == password }
    }
    
    fun getUserById(id: Int): User? {
        return usersStorage.users.find { user -> user.id == id}
    }
    
    fun deleteUserById(id: Int) {
        val deletingUser = getUserById(id)
        usersStorage.users.remove(deletingUser)
    }
    
    fun addNewUser(user: User) {
        usersStorage.users.add(user)
    }
    
}




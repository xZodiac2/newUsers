package com.ilya.data.users

import com.ilya.core.models.User
import com.ilya.domain.models.UserLoginParams
import com.ilya.domain.usersrepository.UserFinder
import com.ilya.domain.usersrepository.UserFinderById
import javax.inject.Inject

class UsersRepository @Inject internal constructor(
    private val usersStorage: UsersStorage
) : UserFinder, UserFinderById {
    
    override fun findUserById(id: Int) : User? {
        return usersStorage.users.find {it.id == id}
    }
    
    override fun findUserByLoginAndPassword(userLoginParams: UserLoginParams): User? {
        return usersStorage.users.find {it.login == userLoginParams.login && it.password == userLoginParams.password}
    }
    
    fun deleteUserById(id: Int) {
        val deletingUser = usersStorage.users.find {it.id == id}
    
        usersStorage.users.remove(deletingUser)
    }
    
    fun addNewUser(user: User) {
        usersStorage.users += user
    }
    
}
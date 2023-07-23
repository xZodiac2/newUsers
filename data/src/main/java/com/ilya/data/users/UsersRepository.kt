package com.ilya.data.users

import com.ilya.core.models.User
import com.ilya.domain.models.login.UserLoginParams
import com.ilya.domain.usersrepository.UserAdder
import com.ilya.domain.usersrepository.UserFinder
import com.ilya.domain.usersrepository.UserFinderById
import javax.inject.Inject

class UsersRepository @Inject internal constructor(
    private val usersStorage: UsersStorage
) : UserFinder, UserFinderById, UserAdder {
    
    override fun findUserById(id: Int) : User? {
        return usersStorage.users.find {it.id == id}
    }
    
    override fun findUserByLoginAndPassword(userLoginParams: UserLoginParams): User? {
        return usersStorage.users.find {it.login == userLoginParams.login && it.password == userLoginParams.password}
    }
    
    override fun addNewUser(user: User) {
        usersStorage.users += user
    }
    
}
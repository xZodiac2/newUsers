package com.ilya.data.users

import com.ilya.core.findFirstOrNull
import com.ilya.data.users.models.UserData
import com.ilya.data.users.models.UserAuthenticateParams
import com.ilya.data.users.repositoryInterface.UserAdder
import com.ilya.data.users.repositoryInterface.UserFinderById
import com.ilya.data.users.repositoryInterface.UserFinderByLoginParams
import javax.inject.Inject

internal class UsersRepository @Inject constructor(
    private val usersStorage: UsersStorage
) : UserFinderById, UserFinderByLoginParams, UserAdder {
    override fun addNewUser(user: UserData) {
        usersStorage.users[user.id] = user
    }
    
    override fun findUserById(id: Int): UserData? {
        return usersStorage.users[id]
    }
    
    override fun findUserByLoginParams(authenticateParams: UserAuthenticateParams): UserData? {
        return usersStorage.users.findFirstOrNull { user -> user.login == authenticateParams.login && user.password == authenticateParams.password }
    }
    
}
package com.ilya.data.users

import com.ilya.core.findFirstOrNull
import com.ilya.data.users.models.UserData
import com.ilya.data.users.models.UserAuthenticateParams
import com.ilya.data.users.repositoryInterface.UserAdder
import com.ilya.data.users.repositoryInterface.UserFinderByLogin
import com.ilya.data.users.repositoryInterface.UserFinderById
import com.ilya.data.users.repositoryInterface.UserFinderByAuthenticateParams
import javax.inject.Inject

internal class UsersRepository @Inject constructor(
    private val usersStorage: UsersStorage
) : UserFinderById, UserFinderByAuthenticateParams, UserFinderByLogin, UserAdder {
    override fun addNewUser(user: UserData) {
        usersStorage.users[user.id] = user
    }
    
    override fun findUserById(id: Int): UserData? {
        return usersStorage.users[id]
    }
    
    override fun findUserByAuthenticateParams(authenticateParams: UserAuthenticateParams): UserData? {
        return usersStorage.users.findFirstOrNull { user -> user.login == authenticateParams.login && user.password == authenticateParams.password }
    }
    
    override fun findUserByLogin(login: String): UserData? {
        return usersStorage.users.findFirstOrNull { user -> user.login == login }
    }
    
}
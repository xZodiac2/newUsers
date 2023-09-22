package com.ilya.data.storage

import com.ilya.core.storage.Storage
import com.ilya.data.error.UsersDataError
import com.ilya.data.models.UserData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class RuntimeUsersStorage @Inject constructor() : Storage<UserData> {
    
    private val users: HashMap<String, UserData> = hashMapOf()
    
    override suspend fun add(data: UserData): Result<Unit> {
        if (users.containsKey(data.login)) {
            return Result.failure(UsersDataError.AlreadyExist)
        }
        
        users[data.login] = data
        return Result.success(Unit)
    }
    
    override suspend fun remove(willRemove: UserData): Result<Unit> {
        if (users.containsKey(willRemove.login)) {
            users.remove(willRemove.login)
            return Result.success(Unit)
        }
        
        return Result.failure(UsersDataError.NotFound)
    }
    
    override suspend fun searchByLogin(login: String): Result<UserData> {
        return when(val foundUser = users[login]) {
            null -> Result.failure(UsersDataError.NotFound)
            else -> Result.success(foundUser)
        }
    }
    
}
package com.ilya.data

import com.ilya.core.Repository
import com.ilya.data.models.UserData
import com.ilya.data.storage.RuntimeUsersStorage
import kotlinx.coroutines.delay
import javax.inject.Inject

class UsersRepository @Inject internal constructor(
    private val runtimeUsersStorage: RuntimeUsersStorage
) : Repository<UserData> {
    
    override suspend fun add(data: UserData): Result<Unit> {
        delay(1000)
        return runtimeUsersStorage.add(data)
    }
    
    override suspend fun remove(willRemove: UserData): Result<Unit> {
        delay(1000)
        return runtimeUsersStorage.remove(willRemove)
    }
    
    override suspend fun searchByLogin(login: String): Result<UserData> {
        delay(1000)
        return runtimeUsersStorage.searchByLogin(login)
    }
}
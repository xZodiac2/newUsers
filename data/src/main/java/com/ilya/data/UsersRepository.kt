package com.ilya.data

import com.ilya.core.Repository
import com.ilya.data.models.UserData
import com.ilya.data.storage.RuntimeUsersStorage
import javax.inject.Inject

class UsersRepository @Inject internal constructor(
    private val runtimeUsersStorage: RuntimeUsersStorage
) : Repository<UserData> {
    
    override fun add(data: UserData): Result<Unit> {
        return runtimeUsersStorage.add(data)
    }
    
    override fun remove(willRemove: UserData): Result<Unit> {
        return runtimeUsersStorage.remove(willRemove)
    }
    
    override fun searchByLogin(login: String): Result<UserData> {
        return runtimeUsersStorage.searchByLogin(login)
    }
}
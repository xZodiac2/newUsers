package com.ilya.data

import android.database.sqlite.SQLiteConstraintException
import com.ilya.core.Repository
import com.ilya.data.database.UsersDao
import com.ilya.data.database.entity.UserData
import com.ilya.data.error.UsersDataError
import kotlinx.coroutines.delay
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val dao: UsersDao,
) : Repository<UserData> {
    
    override suspend fun add(data: UserData): Result<Unit> {
        delay(1000)
        return try {
            dao.registerUser(data)
            Result.success(Unit)
        } catch (e: SQLiteConstraintException) {
            Result.failure(UsersDataError.AlreadyExist)
        }
    }
    
    override suspend fun remove(willRemove: UserData): Result<Unit> {
        delay(1000)
        return when (dao.getUser(willRemove.login)) {
            null -> Result.failure(UsersDataError.NotFound)
            else -> {
                dao.deleteUser(willRemove)
                Result.success(Unit)
            }
        }
    }
    
    override suspend fun searchByLogin(login: String): Result<UserData> {
        delay(1000)
        return when (val foundUser = dao.getUser(login)) {
            null -> Result.failure(UsersDataError.NotFound)
            else -> Result.success(foundUser)
        }
    }
    
}
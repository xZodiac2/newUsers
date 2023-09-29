package com.ilya.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ilya.data.database.entity.UserData

@Dao
interface UsersDao {
    
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun registerUser(user: UserData)
    
    @Query("SELECT * FROM users_table WHERE login LIKE :userLogin")
    suspend fun getUser(userLogin: String): UserData
    
    @Delete()
    suspend fun deleteUser(user: UserData)
    
    @Update
    suspend fun updateUser(user: UserData)
    
}
package com.ilya.data.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ilya.data.database.UsersDao
import com.ilya.data.database.entity.UserData

@Database(entities = [UserData::class], version = 1)
abstract class UsersApplicationDatabase : RoomDatabase() {
    
    abstract fun getDao(): UsersDao
    
    companion object {
        @Volatile
        private lateinit var instance: UsersApplicationDatabase
        
        fun getDatabase(context: Context): UsersApplicationDatabase {
            if (this::instance.isInitialized) {
                return instance
            }
            
            instance = Room.databaseBuilder(
                context.applicationContext,
                UsersApplicationDatabase::class.java,
                "users.db"
            ).build()
            
            return instance
        }
    }
    
}
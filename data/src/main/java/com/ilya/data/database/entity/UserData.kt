package com.ilya.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
data class UserData(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("login")
    val login: String,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("password")
    val passwordHash: String,
)
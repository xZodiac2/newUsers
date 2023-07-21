package com.ilya.data.storage

import com.ilya.core.model.User
import javax.inject.Singleton

@Singleton
internal class UsersStorage {
    val users = mutableListOf<User>(
        User("Ilya", "zodiac", "123"),
        User("maxim", "daun", "hui"),
        User("vania", "yaurodets", "586900")
    )
}
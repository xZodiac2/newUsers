package com.ilya.data.storage

import com.ilya.core.model.User
import javax.inject.Inject
import javax.inject.Singleton

internal class UsersStorage @Inject constructor() {
    val users = mutableListOf<User>(
        User("Ilya", "zodiac", "123"),
        User("maxim", "daun", "hui"),
        User("vania", "yaurodets", "586900")
    )
}
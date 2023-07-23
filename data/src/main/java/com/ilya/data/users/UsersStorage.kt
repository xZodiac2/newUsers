package com.ilya.data.users

import com.ilya.core.models.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class UsersStorage @Inject constructor() {
    val users = mutableListOf<User>(
        User("Ilya", "tomat228", "123"),
        User("SSergey", "Zodiac", "bombom"),
        User("Andrey", "bobik", "kotbegemot")
    )
}
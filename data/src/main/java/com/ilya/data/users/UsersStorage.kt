package com.ilya.data.users

import com.ilya.data.users.models.UserData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class UsersStorage @Inject constructor() {
    val users = hashMapOf(
        0 to UserData("Ilya", "tomat228", "123"),
        1 to UserData("SSergey", "Zodiac", "bombom")
    )
}
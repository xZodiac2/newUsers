package com.ilya.data.users.repositoryInterface

import com.ilya.data.users.models.UserData

interface UserAdder {
    fun addNewUser(user: UserData)
}
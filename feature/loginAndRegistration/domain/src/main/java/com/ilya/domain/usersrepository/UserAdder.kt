package com.ilya.domain.usersrepository

import com.ilya.core.models.User

interface UserAdder {
    fun addNewUser(user: User)
}
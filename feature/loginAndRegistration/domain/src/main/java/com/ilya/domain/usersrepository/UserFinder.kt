package com.ilya.domain.usersrepository

import com.ilya.core.models.User
import com.ilya.domain.models.UserLoginParams

interface UserFinder {
    fun findUserByLoginAndPassword(userLoginParams: UserLoginParams): User?
}
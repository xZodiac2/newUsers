package com.ilya.domain.usersrepository

import com.ilya.core.models.User
import com.ilya.domain.models.login.UserLoginParams

interface UserFinder {
    fun findUserByLoginAndPassword(userLoginParams: UserLoginParams): User?
}
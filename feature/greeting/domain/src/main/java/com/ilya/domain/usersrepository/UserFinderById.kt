package com.ilya.domain.usersrepository

import com.ilya.core.models.User

interface UserFinderById {
    fun findUserById(id: Int): User?
}
package com.ilya.data.users.repositoryInterface

import com.ilya.data.users.models.UserData

interface UserFinderByLogin {
    fun findUserByLogin(login: String): UserData?
}
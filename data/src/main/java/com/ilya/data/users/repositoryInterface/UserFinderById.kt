package com.ilya.data.users.repositoryInterface

import com.ilya.data.users.models.UserData

interface UserFinderById {
    fun findUserById(id: Int): UserData?
}
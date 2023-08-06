package com.ilya.data.users.repositoryInterface

import com.ilya.data.users.models.UserAuthenticateParams
import com.ilya.data.users.models.UserData

interface UserFinderByLoginParams {
    fun findUserByLoginParams(authenticateParams: UserAuthenticateParams): UserData?
}
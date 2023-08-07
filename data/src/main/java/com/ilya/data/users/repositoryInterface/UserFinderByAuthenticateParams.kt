package com.ilya.data.users.repositoryInterface

import com.ilya.data.users.models.UserAuthenticateParams
import com.ilya.data.users.models.UserData

interface UserFinderByAuthenticateParams {
    fun findUserByAuthenticateParams(authenticateParams: UserAuthenticateParams): UserData?
}
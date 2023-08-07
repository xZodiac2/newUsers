package com.ilya.loginandregistration.login.domain.usecases

import com.ilya.data.users.models.UserData
import com.ilya.data.users.models.UserAuthenticateParams
import com.ilya.data.users.repositoryInterface.UserFinderByAuthenticateParams
import com.ilya.loginandregistration.login.domain.exception.UserIsNotFoundByLoginParamsException
import com.ilya.loginandregistration.login.domain.models.LoginParams
import com.ilya.loginandregistration.login.domain.models.UserDataForLogin
import javax.inject.Inject

class FindUserByLoginParamsUseCase @Inject constructor(
    private val userFinderByLoginParams: UserFinderByAuthenticateParams
) {
    operator fun invoke(loginParams: LoginParams): UserDataForLogin {
        val userData: UserData = userFinderByLoginParams.findUserByAuthenticateParams(UserAuthenticateParams(loginParams.login, loginParams.password)) ?: throw UserIsNotFoundByLoginParamsException()
        
        return UserDataForLogin(userData.id)
    }
}
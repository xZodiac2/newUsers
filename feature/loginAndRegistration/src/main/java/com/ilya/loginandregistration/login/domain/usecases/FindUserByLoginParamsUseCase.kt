package com.ilya.loginandregistration.login.domain.usecases

import com.ilya.data.users.models.UserData
import com.ilya.data.users.models.UserAuthenticateParams
import com.ilya.data.users.repositoryInterface.UserFinderByLoginParams
import com.ilya.loginandregistration.login.domain.exception.UserIsNotFoundByLoginParamsException
import com.ilya.loginandregistration.login.domain.models.LoginParams
import com.ilya.loginandregistration.login.domain.models.UserDataForLogin
import javax.inject.Inject

class FindUserByLoginParamsUseCase @Inject constructor(
    private val userFinderByLoginParams: UserFinderByLoginParams
) {
    operator fun invoke(loginParams: LoginParams): UserDataForLogin {
        val userData: UserData = userFinderByLoginParams.findUserByLoginParams(UserAuthenticateParams(loginParams.login, loginParams.password)) ?: throw UserIsNotFoundByLoginParamsException()
        
        userData.apply {
            return UserDataForLogin(name, login, id)
        }
    }
}
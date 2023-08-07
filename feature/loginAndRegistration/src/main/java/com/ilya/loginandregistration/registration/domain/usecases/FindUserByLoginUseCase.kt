package com.ilya.loginandregistration.registration.domain.usecases

import com.ilya.data.users.models.UserData
import com.ilya.data.users.repositoryInterface.UserFinderByLogin
import javax.inject.Inject

/**
 * This usecase use for check, is are user login already used by other user.
 */

class FindUserByLoginUseCase @Inject constructor(
    private val userFinderByLogin: UserFinderByLogin
) {
    operator fun invoke(login: String): UserData? {
        return userFinderByLogin.findUserByLogin(login)
    }
}
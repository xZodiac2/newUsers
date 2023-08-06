package com.ilya.loginandregistration.registration.domain.usecases

import com.ilya.data.users.models.UserData
import com.ilya.data.users.repositoryInterface.UserFinderByLogin
import javax.inject.Inject

class IsLoginUsedByOtherUserUseCase @Inject constructor(
    private val userFinderByLogin: UserFinderByLogin
) {
    operator fun invoke(login: String): Boolean {
        return userFinderByLogin.findUserByLogin(login) != null
    }
}
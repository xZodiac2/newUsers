package com.ilya.domain.usecases

import com.ilya.core.models.User
import com.ilya.core.exceptions.UserNotFoundException
import com.ilya.domain.models.UserLoginParams
import com.ilya.domain.usersrepository.UserFinder

class FindUserByLoginAndPasswordUseCase(
    private val usersFinder: UserFinder,
    private val userLoginParams: UserLoginParams
) {
    fun execute(): User {
        return when(val foundUser = usersFinder.findUserByLoginAndPassword(userLoginParams)) {
            null -> throw UserNotFoundException()
            else -> foundUser
        }
    }
}
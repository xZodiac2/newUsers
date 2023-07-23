package com.ilya.domain.usecases

import com.ilya.core.exceptions.UserNotFoundException
import com.ilya.core.models.User
import com.ilya.domain.usersrepository.UserFinderById

class FindUserByIdUseCase(
    private val userFinderById: UserFinderById,
    private val userId: Int
) {
    fun execute(): User {
        return when(val foundUser = userFinderById.findUserById(userId)) {
            null -> throw UserNotFoundException()
            else -> foundUser
        }
    }
}
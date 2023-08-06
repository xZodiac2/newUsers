package com.ilya.greeting.domain.usecases

import com.ilya.data.users.models.UserData
import com.ilya.data.users.repositoryInterface.UserFinderById
import com.ilya.greeting.domain.exception.UserIsNotFoundByIdException
import com.ilya.greeting.domain.models.User
import javax.inject.Inject

class FindUserByIdUseCase @Inject constructor(
    private val userFinderById: UserFinderById
) {
    operator fun invoke(userId: Int): User {
        val userData: UserData = userFinderById.findUserById(userId) ?: throw UserIsNotFoundByIdException()
        
        userData.apply {
            return User(name, login, password)
        }
    }
}
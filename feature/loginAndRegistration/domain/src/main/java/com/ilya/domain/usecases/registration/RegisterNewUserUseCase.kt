package com.ilya.domain.usecases.registration

import com.ilya.core.models.User
import com.ilya.domain.usersrepository.UserAdder

class RegisterNewUserUseCase(
    private val user: User,
    private val userAdder: UserAdder
) {
    fun execute() {
        userAdder.addNewUser(user)
    }
}
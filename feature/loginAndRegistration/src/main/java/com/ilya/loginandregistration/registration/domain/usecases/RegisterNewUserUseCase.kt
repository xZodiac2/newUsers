package com.ilya.loginandregistration.registration.domain.usecases

import com.ilya.data.users.models.UserData
import com.ilya.data.users.repositoryInterface.UserAdder
import com.ilya.loginandregistration.registration.domain.models.DataOfNewUser
import javax.inject.Inject

class RegisterNewUserUseCase @Inject constructor(
    private val userAdder: UserAdder
){
    operator fun invoke(newUser: DataOfNewUser) {
        userAdder.addNewUser(UserData(newUser.name, newUser.login, newUser.password))
    }
}
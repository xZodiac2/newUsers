package com.ilya.loginandregistration.registration.domain.usecases

import com.ilya.data.users.models.UserData
import com.ilya.data.users.repositoryInterface.UserAdder
import com.ilya.loginandregistration.registration.domain.models.NewUserData
import javax.inject.Inject

class RegisterNewUserUseCase @Inject constructor(
    private val userAdder: UserAdder
){
    operator fun invoke(newUser: NewUserData) {
        userAdder.addNewUser(UserData(newUser.name, newUser.login, newUser.password))
    }
}
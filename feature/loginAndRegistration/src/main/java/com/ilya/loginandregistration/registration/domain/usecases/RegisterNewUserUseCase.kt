package com.ilya.loginandregistration.registration.domain.useCases

import com.ilya.core.UseCase
import com.ilya.data.UsersRepository
import com.ilya.data.error.UsersDataError
import com.ilya.data.models.UserData
import com.ilya.loginandregistration.registration.domain.error.RegistrationDomainError
import com.ilya.loginandregistration.registration.domain.models.NewUserData
import javax.inject.Inject

class RegisterNewUserUseCase @Inject constructor(
    private val usersRepository: UsersRepository,
) : UseCase<Unit> {
    
    override operator fun invoke(data: Any): Result<Unit> {
        val newUserData = data as? NewUserData ?: return Result.failure(RegistrationDomainError.IllegalRegistrationArgument)
        
        return usersRepository.add(UserData(newUserData.name, newUserData.login, newUserData.passwordHash))
            .fold(
                onSuccess = { Result.success(Unit) },
                onFailure = { error ->
                    error as UsersDataError
                    
                    if (error is UsersDataError.AlreadyExist) {
                        Result.failure(RegistrationDomainError.LoginAlreadyUsed)
                    } else {
                        Result.failure(RegistrationDomainError.UnknownError)
                    }
                }
            )
    }
}
package com.ilya.loginandregistration.login.domain.useCases

import com.ilya.core.UseCase
import com.ilya.data.UsersRepository
import com.ilya.data.error.UsersDataError
import com.ilya.loginandregistration.login.domain.error.LoginDomainError
import com.ilya.loginandregistration.login.domain.models.LoggedInUserData
import com.ilya.loginandregistration.login.domain.models.UserLoginParams
import javax.inject.Inject

class FindUserUseCase @Inject constructor(
    private val usersRepository: UsersRepository,
) : UseCase<LoggedInUserData> {
    
    override operator fun invoke(data: Any): Result<LoggedInUserData> {
        val loginParams = data as? UserLoginParams ?: return Result.failure(LoginDomainError.WrongLoginArgument)
        
        return usersRepository.searchByLogin(loginParams.login)
            .fold(
                onSuccess = { userData ->
                    if (userData.passwordHash == loginParams.passwordHash) {
                        Result.success(LoggedInUserData(userData.login))
                    } else {
                        Result.failure(LoginDomainError.WrongLoginOrPassword)
                    }
                },
                
                onFailure = { error ->
                    error as UsersDataError
                    
                    if (error is UsersDataError.NotFound) {
                        Result.failure(LoginDomainError.WrongLoginOrPassword)
                    } else {
                        Result.failure(LoginDomainError.UnknownError)
                    }
                    
                }
            )
    }
    
}
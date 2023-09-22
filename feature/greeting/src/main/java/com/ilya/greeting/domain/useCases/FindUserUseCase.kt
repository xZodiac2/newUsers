package com.ilya.greeting.domain.useCases

import com.ilya.core.UseCase
import com.ilya.data.UsersRepository
import com.ilya.data.error.UsersDataError
import com.ilya.greeting.domain.error.GreetingDomainError
import com.ilya.greeting.domain.models.GreetingUserData
import javax.inject.Inject

class FindUserUseCase @Inject constructor(
    private val usersRepository: UsersRepository,
) : UseCase<GreetingUserData> {
    
    override suspend fun execute(data: Any): Result<GreetingUserData> {
        return usersRepository.searchByLogin(data.toString())
            .fold(
                onSuccess = { Result.success(GreetingUserData(it.name)) },
                onFailure = { error ->
                    error as UsersDataError
                    
                    if (error is UsersDataError.NotFound) {
                        Result.failure(GreetingDomainError.UserNotFound)
                    } else {
                        Result.failure(GreetingDomainError.UnknownError)
                    }
                }
            )
    }
}
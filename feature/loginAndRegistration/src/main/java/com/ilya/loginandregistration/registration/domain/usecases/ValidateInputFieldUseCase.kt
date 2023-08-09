package com.ilya.loginandregistration.registration.domain.useCases

import com.ilya.core.UseCase
import com.ilya.loginandregistration.registration.domain.error.ErrorList
import com.ilya.loginandregistration.registration.domain.models.ErrorListWrapper
import com.ilya.loginandregistration.registration.domain.validators.RegistrationFormValidator

class ValidateInputFieldUseCase(
    private vararg val validators: RegistrationFormValidator
) : UseCase<Unit> {
    
    override fun invoke(data: Any): Result<Unit> {
        val errorList: ErrorList = validators.mapNotNull { it.validate(data.toString()) }
        
        return if (errorList.all { it == null }) {
            Result.success(Unit)
        } else {
            Result.failure(ErrorListWrapper(errorList))
        }
    }
}
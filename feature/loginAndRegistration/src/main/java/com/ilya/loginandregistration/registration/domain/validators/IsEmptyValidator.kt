package com.ilya.loginandregistration.registration.domain.validators

import com.ilya.loginandregistration.registration.domain.error.RegistrationValidationError

class IsEmptyValidator : RegistrationFormValidator {
    
    override fun validate(data: String): RegistrationValidationError? {
        return when {
            data.isEmpty() -> RegistrationValidationError.FieldIsEmpty
            else -> null
        }
    }
}
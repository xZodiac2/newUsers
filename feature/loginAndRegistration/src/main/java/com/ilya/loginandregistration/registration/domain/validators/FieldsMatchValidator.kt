package com.ilya.loginandregistration.registration.domain.validators

import com.ilya.loginandregistration.registration.domain.error.RegistrationValidationError

class FieldsMatchValidator(
    private val compareWith: String
) : RegistrationFormValidator {
    
    override fun validate(data: String): RegistrationValidationError? {
        return when {
            data != compareWith -> RegistrationValidationError.FieldsDoNotMatch
            else -> null
        }
    }
}
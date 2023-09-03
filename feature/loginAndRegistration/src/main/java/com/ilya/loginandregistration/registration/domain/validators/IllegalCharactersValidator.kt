package com.ilya.loginandregistration.registration.domain.validators

import com.ilya.loginandregistration.registration.domain.error.RegistrationValidationError

class IllegalCharactersValidator(
    private val validationRegex: Regex
) : RegistrationFormValidator {
    
    override fun validate(data: String): RegistrationValidationError? {
        return when {
            data.contains(validationRegex) -> RegistrationValidationError.IllegalCharacter
            else -> null
        }
    }
}
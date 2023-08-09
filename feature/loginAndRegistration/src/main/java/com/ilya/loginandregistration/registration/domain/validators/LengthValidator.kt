package com.ilya.loginandregistration.registration.domain.validators

import com.ilya.loginandregistration.registration.domain.error.RegistrationValidationError
import com.ilya.loginandregistration.registration.domain.models.NecessaryLength

class LengthValidator(
    private val necessaryLength: NecessaryLength
) : RegistrationFormValidator {
    
    override fun validate(data: String): RegistrationValidationError? {
        return if (data.length < necessaryLength.value) {
            when(necessaryLength) {
                is NecessaryLength.Password -> RegistrationValidationError.LengthError.Password
                is NecessaryLength.Login -> RegistrationValidationError.LengthError.Login
            }
        } else {
            null
        }
    }
}
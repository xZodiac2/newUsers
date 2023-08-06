package com.ilya.loginandregistration.registration.domain.usecases.inputValidators

import com.ilya.loginandregistration.registration.domain.error.RegistrationError
import com.ilya.loginandregistration.registration.domain.models.InputFieldsErrors
import com.ilya.loginandregistration.registration.domain.models.InputFieldsValues

class CheckRepeatedPasswordFieldUseCase {
    operator fun invoke(oldValidationResult: InputFieldsErrors, inputFieldsValues: InputFieldsValues): InputFieldsErrors {
        
        val password = inputFieldsValues.password
        val repeatedPassword = inputFieldsValues.repeatedPassword
        
        val validationResult: RegistrationError? = if (repeatedPassword != password) {
            RegistrationError.FieldsDoNotMatch
        } else if (repeatedPassword.length < NECESSARY_REPEATED_PASSWORD_LENGTH) {
            RegistrationError.ShortFieldLength
        } else {
            null
        }
        
        oldValidationResult.apply {
            return InputFieldsErrors(this.name, this.login, this.password, validationResult)
        }
        
    }
    
    companion object {
        private const val NECESSARY_REPEATED_PASSWORD_LENGTH = 8
    }
}
package com.ilya.loginandregistration.registration.domain.usecases.inputValidators

import com.ilya.loginandregistration.registration.domain.error.RegistrationError
import com.ilya.loginandregistration.registration.domain.models.InputFieldsErrors
import com.ilya.loginandregistration.registration.domain.models.InputFieldsValues
import javax.inject.Inject

class CheckRepeatedPasswordFieldValueUseCase @Inject constructor() {
    operator fun invoke(previousValidationResult: InputFieldsErrors, inputFieldsValues: InputFieldsValues): InputFieldsErrors {
        
        val password = inputFieldsValues.password
        val repeatedPassword = inputFieldsValues.repeatedPassword
        
        val validationResult: RegistrationError? = if (repeatedPassword != password) {
            RegistrationError.FieldsDoNotMatch
        } else if (repeatedPassword.length < NECESSARY_REPEATED_PASSWORD_LENGTH) {
            RegistrationError.ShortFieldLength
        } else {
            null
        }
        
        previousValidationResult.apply {
            return InputFieldsErrors(this.name, this.login, this.password, validationResult)
        }
        
    }
    
    private companion object {
        const val NECESSARY_REPEATED_PASSWORD_LENGTH = 8
    }
}
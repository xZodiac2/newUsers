package com.ilya.loginandregistration.registration.domain.usecases.inputValidators

import com.ilya.loginandregistration.registration.domain.error.RegistrationError
import com.ilya.loginandregistration.registration.domain.models.InputFieldsErrors
import com.ilya.loginandregistration.registration.domain.models.InputFieldsValues
import javax.inject.Inject

class CheckPasswordFieldValueUseCase @Inject constructor(
    private val checkRepeatedPasswordFieldValueUseCase: CheckRepeatedPasswordFieldValueUseCase
) {
    operator fun invoke(oldValidationResult: InputFieldsErrors, inputFieldsValues: InputFieldsValues): InputFieldsErrors {
        val validationResult: RegistrationError? = if (inputFieldsValues.password.length < NECESSARY_PASSWORD_LENGTH) RegistrationError.ShortFieldLength else null
        return checkRepeatedPasswordFieldValueUseCase(InputFieldsErrors(oldValidationResult.name, oldValidationResult.login, validationResult), inputFieldsValues)
    }
    
    companion object {
        private const val NECESSARY_PASSWORD_LENGTH = 8
    }
}
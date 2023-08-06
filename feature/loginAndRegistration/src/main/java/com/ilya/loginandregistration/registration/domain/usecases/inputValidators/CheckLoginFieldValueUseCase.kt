package com.ilya.loginandregistration.registration.domain.usecases.inputValidators

import com.ilya.loginandregistration.registration.domain.error.RegistrationError
import com.ilya.loginandregistration.registration.domain.models.InputFieldsErrors
import com.ilya.loginandregistration.registration.domain.models.InputFieldsValues

class CheckLoginFieldValueUseCase {
    operator fun invoke(oldValidationResult: InputFieldsErrors, inputFieldsValues: InputFieldsValues): InputFieldsErrors {
        val validationResult: RegistrationError? = if (inputFieldsValues.login.isEmpty()) RegistrationError.FieldIsEmpty else null
        return CheckPasswordFieldValueUseCase()(InputFieldsErrors(oldValidationResult.name, validationResult), inputFieldsValues)
    }
}
package com.ilya.loginandregistration.registration.domain.usecases.inputValidators

import com.ilya.loginandregistration.registration.domain.error.RegistrationError
import com.ilya.loginandregistration.registration.domain.models.InputFieldsErrors
import com.ilya.loginandregistration.registration.domain.models.InputFieldsValues

class CheckNameFieldValueUseCase {
    operator fun invoke(inputFieldsValues: InputFieldsValues): InputFieldsErrors {
        val validationResult: RegistrationError? = if (inputFieldsValues.name.isEmpty()) RegistrationError.FieldIsEmpty else null
        return CheckLoginFieldValueUseCase()(InputFieldsErrors(validationResult), inputFieldsValues)
    }
}
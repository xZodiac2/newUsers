package com.ilya.loginandregistration.registration.domain.usecases.inputValidators

import com.ilya.loginandregistration.registration.domain.error.RegistrationError
import com.ilya.loginandregistration.registration.domain.models.InputFieldsErrors
import com.ilya.loginandregistration.registration.domain.models.InputFieldsValues
import javax.inject.Inject

class CheckLoginFieldValueUseCase @Inject constructor(
    private val checkPasswordFieldValueUseCase: CheckPasswordFieldValueUseCase
) {
    operator fun invoke(oldValidationResult: InputFieldsErrors, inputFieldsValues: InputFieldsValues): InputFieldsErrors {
        val validationResult: RegistrationError? = if (inputFieldsValues.login.isEmpty()) RegistrationError.FieldIsEmpty else null
        return checkPasswordFieldValueUseCase(InputFieldsErrors(oldValidationResult.name, validationResult), inputFieldsValues)
    }
}
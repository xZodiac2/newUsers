package com.ilya.loginandregistration.registration.domain.usecases.inputValidators

import com.ilya.loginandregistration.registration.domain.error.RegistrationError
import com.ilya.loginandregistration.registration.domain.models.InputFieldsErrors
import com.ilya.loginandregistration.registration.domain.models.InputFieldsValues
import javax.inject.Inject

class CheckNameFieldValueUseCase @Inject constructor(
    private val checkLoginFieldValueUseCase: CheckLoginFieldValueUseCase
) {
    operator fun invoke(inputFieldsValues: InputFieldsValues): InputFieldsErrors {
        val validationResult: RegistrationError? = if (inputFieldsValues.name.isEmpty()) RegistrationError.FieldIsEmpty else null
        return checkLoginFieldValueUseCase(InputFieldsErrors(validationResult), inputFieldsValues)
    }
}
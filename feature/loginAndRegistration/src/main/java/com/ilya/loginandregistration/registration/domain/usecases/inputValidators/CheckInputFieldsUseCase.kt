package com.ilya.loginandregistration.registration.domain.usecases.inputValidators

import com.ilya.loginandregistration.registration.domain.models.InputFieldsErrors
import com.ilya.loginandregistration.registration.domain.models.InputFieldsValues
import javax.inject.Inject

class CheckInputFieldsUseCase @Inject constructor(
    private val checkNameFieldValueUseCase: CheckNameFieldValueUseCase
) {
    
    /**
     * This method starts sequence of validation input fields.
     * Use cases are invokes step by step and validate one of the input fields values which need be validate.
     * Each use case create new instance of the InputFieldsError class and put current error with previous errors.
     * Finally, this method returns finished instance of the InputFieldsError class.
     */
    
    operator fun invoke(inputFieldsValues: InputFieldsValues): InputFieldsErrors {
        return checkNameFieldValueUseCase(inputFieldsValues)
    }
}
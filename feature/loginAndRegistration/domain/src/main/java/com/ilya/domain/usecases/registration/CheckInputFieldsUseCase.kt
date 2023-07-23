package com.ilya.domain.usecases.registration

import com.ilya.domain.error.RegistrationError
import com.ilya.domain.models.registration.InputFieldsValidationResult
import com.ilya.domain.models.registration.RegisteringUserParams

class CheckInputFieldsUseCase(private val registeringUserParams: RegisteringUserParams) {
    fun execute(): InputFieldsValidationResult {
        val necessaryPasswordLength = 8
        
        var nameValidationResult: RegistrationError? = null
        var loginValidationResult: RegistrationError? = null
        var passwordValidationResult: RegistrationError? = null
        var repeatedPasswordValidationResult: RegistrationError? = null
        
        if (registeringUserParams.name.isEmpty()) {
            nameValidationResult = RegistrationError.FieldIsEmpty
        }
        if (registeringUserParams.login.isEmpty()) {
            loginValidationResult = RegistrationError.FieldIsEmpty
        }
        if (registeringUserParams.password.length < necessaryPasswordLength) {
            passwordValidationResult = RegistrationError.ShortFieldLength
        }
        if (registeringUserParams.password != registeringUserParams.repeatedPassword) {
            repeatedPasswordValidationResult = RegistrationError.FieldsDoNotMatch
        } else if (registeringUserParams.repeatedPassword.length < necessaryPasswordLength) {
            repeatedPasswordValidationResult = RegistrationError.ShortFieldLength
        }
        
        
        return InputFieldsValidationResult(nameValidationResult, loginValidationResult, passwordValidationResult, repeatedPasswordValidationResult)
    }
}
package com.ilya.loginandregistration.registration.domain.error

import com.ilya.core.abstractClasses.validation.ValidationError

typealias ErrorList = List<RegistrationValidationError?>

sealed class RegistrationValidationError : ValidationError() {
    
    sealed class LengthError : RegistrationValidationError() {
        object Login : LengthError()
        object Password : LengthError()
    }
    
    object FieldIsEmpty : RegistrationValidationError()
    object IllegalCharacter : RegistrationValidationError()
    object FieldsDoNotMatch : RegistrationValidationError()
    
}
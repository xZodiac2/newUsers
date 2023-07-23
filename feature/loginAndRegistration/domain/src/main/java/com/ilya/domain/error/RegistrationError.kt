package com.ilya.domain.error

sealed class RegistrationError {
    
    lateinit var errorMessage: String
    
    object FieldIsEmpty : RegistrationError()
    object ShortFieldLength : RegistrationError()
    object FieldsDoNotMatch : RegistrationError()
}
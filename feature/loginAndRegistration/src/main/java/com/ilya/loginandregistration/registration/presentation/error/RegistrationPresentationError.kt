package com.ilya.loginandregistration.registration.presentation.error

import com.ilya.core.TextReference
import com.ilya.core.abstractClasses.cleanArchitecture.PresentationError
import com.ilya.loginandregistration.R

typealias PresentationErrorList = List<RegistrationPresentationError>

sealed class RegistrationPresentationError(override val textReference: TextReference) : PresentationError(textReference) {
    
    sealed class LengthError(override val textReference: TextReference) : RegistrationPresentationError(textReference) {
        object Login : LengthError(TextReference.Resource(R.string.text_registration_login_is_short))
        object Password : LengthError(TextReference.Resource(R.string.text_registration_password_is_small_error))
    }
    
    object IllegalCharacter : RegistrationPresentationError(TextReference.Resource(R.string.text_registration_illegal_characters_error))
    object LoginAlreadyUsed : RegistrationPresentationError(TextReference.Resource(R.string.text_registration_login_is_already_used))
    object FieldsDoNotMatch : RegistrationPresentationError(TextReference.Resource(R.string.text_registration_fields_do_not_match_error))
    object FieldIsEmpty : RegistrationPresentationError(TextReference.Resource(R.string.text_registration_empty_field_error))
    
}
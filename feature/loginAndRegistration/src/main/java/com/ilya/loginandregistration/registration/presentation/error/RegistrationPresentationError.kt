package com.ilya.loginandregistration.registration.presentation.error

import com.ilya.core.TextReference
import com.ilya.core.abstractClasses.cleanArchitecture.PresentationError
import com.ilya.loginandregistration.R
import com.ilya.loginandregistration.registration.presentation.veiwModel.RegistrationViewModel

typealias PresentationErrorList = List<RegistrationPresentationError>

sealed class RegistrationPresentationError(override val textReference: TextReference) :
    PresentationError(textReference) {
    
    sealed class LengthError(override val textReference: TextReference) : RegistrationPresentationError(textReference) {
        
        object Login : LengthError(
            TextReference.Resource(
                R.string.text_registration_login_is_short,
                listOf(RegistrationViewModel.NECESSARY_LOGIN_LENGTH)
            )
        )
        
        object Password : LengthError(
            TextReference.Resource(
                R.string.text_registration_password_is_small_error,
                listOf(RegistrationViewModel.NECESSARY_PASSWORD_LENGTH)
            )
        )
    }
    
    object IllegalCharacter :
        RegistrationPresentationError(TextReference.Resource(R.string.text_registration_illegal_characters_error))
    
    object LoginAlreadyUsed :
        RegistrationPresentationError(TextReference.Resource(R.string.text_registration_login_is_already_used))
    
    object FieldsDoNotMatch :
        RegistrationPresentationError(TextReference.Resource(R.string.text_registration_fields_do_not_match_error))
    
    object FieldIsEmpty :
        RegistrationPresentationError(TextReference.Resource(R.string.text_registration_empty_field_error))
    
    object UnknownError :
        RegistrationPresentationError(TextReference.Resource(R.string.text_registration_error_unknown))
    
    object SomethingWentWrong :
        RegistrationPresentationError(TextReference.Resource(R.string.text_login_error_something_went_wrong))
    
}

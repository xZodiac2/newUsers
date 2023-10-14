package com.ilya.loginandregistration.login.presentation.error

import com.ilya.core.TextReference
import com.ilya.core.abstractClasses.cleanArchitecture.PresentationError
import com.ilya.loginandregistration.R

sealed class LoginPresentationError(override val textReference: TextReference) : PresentationError(textReference) {
    object WrongLoginOrPassword : LoginPresentationError(TextReference.Resource(R.string.text_error_invalid_input))
    object UnknownError : LoginPresentationError(TextReference.Resource(R.string.text_login_error_unknown))
    object SomethingWentWrong :
        LoginPresentationError(TextReference.Resource(R.string.text_login_error_something_went_wrong))
}

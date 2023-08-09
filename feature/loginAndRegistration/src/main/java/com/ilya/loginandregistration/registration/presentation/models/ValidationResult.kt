package com.ilya.loginandregistration.registration.presentation.models

import com.ilya.loginandregistration.registration.presentation.error.PresentationErrorList


data class ValidationResult(
    val name: PresentationErrorList?,
    val login: PresentationErrorList?,
    val password: PresentationErrorList?,
    val repeatedPassword: PresentationErrorList?
)
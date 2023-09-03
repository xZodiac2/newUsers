package com.ilya.loginandregistration.registration.presentation.state

import com.ilya.loginandregistration.registration.presentation.error.RegistrationPresentationError
import com.ilya.loginandregistration.registration.presentation.models.ValidationResult

data class RegistrationViewState(
    val validationResult: ValidationResult,
    val registrationError: RegistrationPresentationError?,
    val isUserSuccessfullyRegistered: Boolean,
)

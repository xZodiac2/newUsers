package com.ilya.loginandregistration.registration.presentation.state

import com.ilya.core.enums.ViewVisibility
import com.ilya.loginandregistration.registration.presentation.error.RegistrationPresentationError
import com.ilya.loginandregistration.registration.presentation.models.ValidationResult

data class RegistrationViewState(
    val validationResult: ValidationResult,
    val registrationError: RegistrationPresentationError?,
    val buttonVisibility: ViewVisibility,
    val progressBarVisibility: ViewVisibility,
)

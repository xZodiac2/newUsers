package com.ilya.loginandregistration.registration.presentation.state

import com.ilya.loginandregistration.registration.presentation.models.ValidationResult

data class RegistrationViewState(
    val validationResult: ValidationResult,
    val isUserSuccessfullyRegistered: Boolean
)

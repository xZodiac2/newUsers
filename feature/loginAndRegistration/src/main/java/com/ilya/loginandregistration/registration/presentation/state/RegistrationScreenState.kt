package com.ilya.loginandregistration.registration.presentation.state

import androidx.annotation.StringRes
import com.ilya.core.enums.ViewVisibility
import com.ilya.loginandregistration.R
import com.ilya.loginandregistration.registration.presentation.error.RegistrationPresentationError
import com.ilya.loginandregistration.registration.presentation.models.ValidationResult

data class RegistrationScreenState(
    val validationResult: ValidationResult = ValidationResult(null, null, null, null),
    val registrationError: RegistrationPresentationError? = null,
    val buttonVisibility: ViewVisibility = ViewVisibility.VISIBLE,
    val progressBarVisibility: ViewVisibility = ViewVisibility.GONE,
    val errorVisibility: ViewVisibility = ViewVisibility.GONE,
    @StringRes val toastMessageOnSuccessfulRegister: Int = R.string.text_registration_user_added_successfully,
)

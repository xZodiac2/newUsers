package com.ilya.loginandregistration.registration.presentation.callback

import com.ilya.loginandregistration.registration.presentation.models.InputFieldValues
import kotlinx.coroutines.Job

interface RegistrationViewCallback {
    fun onRegisterClick(inputFieldValues: InputFieldValues): Job
}
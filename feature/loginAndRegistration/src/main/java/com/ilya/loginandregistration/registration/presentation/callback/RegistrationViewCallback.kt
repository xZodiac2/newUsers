package com.ilya.loginandregistration.registration.presentation.callback

import com.ilya.loginandregistration.registration.presentation.models.InputFieldValues

interface RegistrationViewCallback {
    fun onRegisterClick(inputFieldValues: InputFieldValues)
}
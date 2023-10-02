package com.ilya.loginandregistration.registration.presentation.state

sealed class RegistrationWaitingState {
    object WaitingForResponse : RegistrationWaitingState()
    object WaitingForUser : RegistrationWaitingState()
}

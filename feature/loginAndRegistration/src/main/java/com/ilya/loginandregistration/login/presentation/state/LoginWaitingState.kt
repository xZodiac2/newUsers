package com.ilya.loginandregistration.login.presentation.state

sealed class LoginWaitingState {
    object WaitingForUser : LoginWaitingState()
    object WaitingForResponse : LoginWaitingState()
}

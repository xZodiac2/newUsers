package com.ilya.greeting.presentation.state

sealed class GreetingWaitingState {
    object WaitingForResponse : GreetingWaitingState()
    object WaitingForUser : GreetingWaitingState()
}
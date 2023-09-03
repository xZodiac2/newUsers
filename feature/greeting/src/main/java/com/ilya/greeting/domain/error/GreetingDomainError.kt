package com.ilya.greeting.domain.error

import com.ilya.core.abstractClasses.cleanArchitecture.DomainError

sealed class GreetingDomainError : DomainError() {
    object UserNotFound : GreetingDomainError()
    object UnknownError : GreetingDomainError()
}

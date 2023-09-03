package com.ilya.loginandregistration.registration.domain.error

import com.ilya.core.abstractClasses.cleanArchitecture.DomainError

sealed class RegistrationDomainError : DomainError() {
    object IllegalRegistrationArgument : RegistrationDomainError()
    object LoginAlreadyUsed : RegistrationDomainError()
    object UnknownError : RegistrationDomainError()
}
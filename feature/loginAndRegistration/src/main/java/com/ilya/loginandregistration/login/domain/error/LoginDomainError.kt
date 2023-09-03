package com.ilya.loginandregistration.login.domain.error

import com.ilya.core.abstractClasses.cleanArchitecture.DomainError

sealed class LoginDomainError : DomainError() {
    object WrongLoginArgument : LoginDomainError()
    object WrongLoginOrPassword : LoginDomainError()
    object UnknownError : LoginDomainError()
}

package com.ilya.core.abstractClasses.cleanArchitecture

import com.ilya.core.abstractClasses.UsersApplicationError

abstract class DomainError(override val message: String? = null) : UsersApplicationError(message)
package com.ilya.core.abstractClasses.cleanArchitecture

import com.ilya.core.abstractClasses.UsersApplicationError

abstract class DataError(override val message: String? = null) : UsersApplicationError(message)
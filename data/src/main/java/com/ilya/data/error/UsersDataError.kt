package com.ilya.data.error

import com.ilya.core.abstractClasses.cleanArchitecture.DataError

sealed class UsersDataError : DataError() {
    object NotFound : UsersDataError()
    object AlreadyExist : UsersDataError()
}
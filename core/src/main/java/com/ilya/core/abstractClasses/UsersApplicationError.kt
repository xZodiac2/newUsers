package com.ilya.core.abstractClasses

abstract class UsersApplicationError internal constructor(override val message: String? = null) : Throwable(message) {
}
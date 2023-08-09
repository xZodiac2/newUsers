package com.ilya.core

import com.ilya.core.abstractClasses.validation.ValidationError

interface Validator<in T, out E : ValidationError> {
    fun validate(data: T): E?
}
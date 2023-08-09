package com.ilya.loginandregistration.registration.domain.models

import com.ilya.core.abstractClasses.validation.ValidationError
import com.ilya.loginandregistration.registration.domain.error.ErrorList


data class ErrorListWrapper(val errorList: ErrorList) : ValidationError()
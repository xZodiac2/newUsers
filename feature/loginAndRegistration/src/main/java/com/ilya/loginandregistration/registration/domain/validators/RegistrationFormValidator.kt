package com.ilya.loginandregistration.registration.domain.validators

import com.ilya.core.Validator
import com.ilya.loginandregistration.registration.domain.error.RegistrationValidationError

interface RegistrationFormValidator : Validator<String, RegistrationValidationError>
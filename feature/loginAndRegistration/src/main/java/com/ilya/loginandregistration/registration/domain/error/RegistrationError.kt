package com.ilya.loginandregistration.registration.domain.error

import androidx.annotation.StringRes
import com.ilya.core.abstractClasses.UsersApplicationError
import com.ilya.loginandregistration.R

sealed class RegistrationError(@StringRes override val stringId: Int) : UsersApplicationError(stringId) {
    object FieldIsEmpty : RegistrationError(R.string.text_registration_empty_field_error)
    object ShortFieldLength : RegistrationError(R.string.text_registration_password_is_small_error)
    object FieldsDoNotMatch : RegistrationError(R.string.text_registration_passwords_do_not_match_error)
}
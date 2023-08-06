package com.ilya.loginandregistration.registration.presentation.models

import com.google.android.material.textfield.TextInputLayout

data class InputFieldsLayouts(
    val nameLayout: TextInputLayout,
    val loginLayout: TextInputLayout,
    val passwordLayout: TextInputLayout,
    val repeatedPasswordLayout: TextInputLayout
)

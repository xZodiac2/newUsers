package com.ilya.usersupgrade

import com.google.android.material.textfield.TextInputEditText

open class InputValidator(
    private val inputField: TextInputEditText
) {
    fun filled() = inputField.text.toString().isNotEmpty()
}
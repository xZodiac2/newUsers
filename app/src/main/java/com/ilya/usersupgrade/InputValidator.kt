package com.ilya.usersupgrade

import com.google.android.material.textfield.TextInputEditText

open class InputValidator(
    private val inputField: TextInputEditText
) {
    fun isEmpty() = inputField.text.toString().isEmpty()
}
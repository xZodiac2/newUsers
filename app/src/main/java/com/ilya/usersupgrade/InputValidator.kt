package com.ilya.usersupgrade

import com.google.android.material.textfield.TextInputEditText

open class InputValidator() {
    fun isFilled(text: String) = text.isNotEmpty()
}
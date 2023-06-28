package com.ilya.usersupgrade

import com.google.android.material.textfield.TextInputEditText

class PasswordInputValidator(
    private val passwordInput: TextInputEditText,
    private val repeatedPasswordInput: TextInputEditText? = null,
    private val necessaryPasswordLength: Int = 8
) : InputValidator(passwordInput) {
    
    fun isNormalLength(): Boolean = passwordInput.length() >= necessaryPasswordLength
    
    fun passwordsEquals(): Boolean {
        if (repeatedPasswordInput == null) return true
        
        return passwordInput.text.toString() == repeatedPasswordInput.text.toString()
    }
}
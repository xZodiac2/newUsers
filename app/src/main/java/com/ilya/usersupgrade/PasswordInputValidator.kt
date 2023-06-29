package com.ilya.usersupgrade

import com.google.android.material.textfield.TextInputEditText

class PasswordInputValidator() : InputValidator() {
    
    fun isNormalLength(password: String, necessaryPasswordLength: Int = 8) = password.length >= necessaryPasswordLength
    fun isPasswordsEquals(password: String, repeatedPassword: String) = password == repeatedPassword
    
}
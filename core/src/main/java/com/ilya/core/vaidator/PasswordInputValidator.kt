package com.ilya.core.vaidator


class PasswordInputValidator() : InputValidator() {
    
    fun isNormalLength(password: String, necessaryPasswordLength: Int = 8) = password.length >= necessaryPasswordLength
    fun isPasswordsEquals(password: String, repeatedPassword: String) = password == repeatedPassword
    
}
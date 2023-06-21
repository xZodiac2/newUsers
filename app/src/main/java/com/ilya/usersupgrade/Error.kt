package com.ilya.usersupgrade


sealed class Error(val stringId: Int) : Throwable() {
    object InvalidInputError : Error(R.string.text_error_invalid_input)
    
}
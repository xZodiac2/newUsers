package com.ilya.usersupgrade

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class InputTextValidator(
    private val inputLayout: TextInputLayout,
    private val inputField: TextInputEditText
) : TextWatcher {
    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
    
    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        if (s.length >= inputLayout.counterMaxLength) {
            inputField.setText(s.replace(".$".toRegex(), ""))
        }
    }
    
    override fun afterTextChanged(s: Editable) {}
}
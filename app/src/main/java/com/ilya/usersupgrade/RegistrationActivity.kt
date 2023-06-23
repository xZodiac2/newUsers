package com.ilya.usersupgrade

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.ilya.usersupgrade.databinding.ActivityRegistrationBinding

class RegistrationActivity : AppCompatActivity() {
    
    private lateinit var views: ActivityRegistrationBinding
    private lateinit var usersRepository: UsersRepository
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        views = ActivityRegistrationBinding.inflate(layoutInflater)
        usersRepository = (applicationContext as UsersApplication).usersRepository
        setContentView(views.root)
        
        views.btnRegister.setOnClickListener(this::register)
        
    }
    
    private fun register(view: View) = with(views) {
        removeErrors()
        
        val userName = etName.text.toString()
        val userLogin = etLogin.text.toString()
        val userPassword = etPassword.text.toString()
        val userRepeatedPassword = etRepeatedPassword.text.toString()
        
        val maxInputLength = 25
    
        if (userName.isEmpty()) {
            onInputError(Error.InputError.EmptyFieldError, etName)
            return
        }
        
        if (userLogin.isEmpty()) {
            onInputError(Error.InputError.EmptyFieldError, etLogin)
            return
        }
        
        if (userName.length > maxInputLength) {
            onInputError(Error.InputError.LengthError, etName)
            return
        }
        
        if (userLogin.length > maxInputLength) {
            onInputError(Error.InputError.LengthError, etLogin)
            return
        }
        
        if (userPassword.length < 8) {
            onPasswordsError(Error.InputError.PasswordIsShort, etPassword)
            return
        }
        
        if (userRepeatedPassword != userPassword) {
            onPasswordsError(Error.InputError.PasswordsDoNotMatchError, etRepeatedPassword)
            return
        }
        
        val user = User(userName, userLogin, userPassword)
        
        usersRepository.addNewUser(user)
        
        Toast.makeText(this@RegistrationActivity, getString(R.string.text_registration_user_added_successfully), Toast.LENGTH_SHORT).show()
        
        finish()
    }
    
    private fun onPasswordsError(passwordError: Error, passwordField: View) = with(views) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        
        imm.showSoftInput(passwordField, InputMethodManager.SHOW_IMPLICIT)
    
        passwordField.requestFocus()
        
        tvErrorPassword.text = passwordError.extract(this@RegistrationActivity)
        tvErrorPassword.visibility = View.VISIBLE
        
        etPassword.inputType = InputType.TYPE_CLASS_TEXT
        etRepeatedPassword.inputType = InputType.TYPE_CLASS_TEXT
    }
    
    private fun onInputError(inputError: Error, inputField: View) = with(views) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        
        imm.showSoftInput(inputField, InputMethodManager.SHOW_IMPLICIT)
        
        inputField.requestFocus()
    
        (when (inputField) {
            etName -> tvNameLengthError
            else -> tvLoginLengthError
        }).apply {
            text = inputError.extract(this@RegistrationActivity)
            visibility = View.VISIBLE
        }
    }
    
    private fun removeErrors() = with(views) {
        tvErrorPassword.visibility = View.GONE
        tvNameLengthError.visibility = View.GONE
        tvLoginLengthError.visibility = View.GONE
    }
    
}
package com.ilya.usersupgrade

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.ilya.usersupgrade.databinding.ActivityRegistrationBinding


class RegistrationActivity : AppCompatActivity() {
    
    private lateinit var views: ActivityRegistrationBinding
    private lateinit var usersRepository: UsersRepository
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        views = ActivityRegistrationBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
        }
        usersRepository = (applicationContext as UsersApplication).usersRepository
        
        views.apply {
            btnRegister.setOnClickListener(this@RegistrationActivity::register)
            
            etName.addTextChangedListener(InputTextValidator(nameInputLayout, etName))
            etLogin.addTextChangedListener(InputTextValidator(loginInputLayout, etLogin))
            etPassword.addTextChangedListener(InputTextValidator(passwordInputLayout, etPassword))
            etRepeatedPassword.addTextChangedListener(InputTextValidator(repeatedPasswordInputLayout, etRepeatedPassword))
            
        }
        
    }
    
    private fun register(view: View) = with(views) {
        val userName = etName.text.toString()
        val userLogin = etLogin.text.toString()
        val userPassword = etPassword.text.toString()
        val repeatedUserPassword = etRepeatedPassword.text.toString()
        
        if (userName.isEmpty()) {
            onInputError(nameInputLayout, etName, Error.InputError.EmptyFieldError)
        }
        
        if (userLogin.isEmpty()) {
            onInputError(loginInputLayout, etLogin, Error.InputError.EmptyFieldError)
        }
        
        if (userPassword.length < NECESSARY_PASSWORD_LENGTH) {
            onInputError(passwordInputLayout, etPassword, Error.InputError.PasswordLengthError)
        }
        
        if (userPassword != repeatedUserPassword) {
            onInputError(repeatedPasswordInputLayout, etRepeatedPassword, Error.InputError.PasswordsDoNotMatchError)
        }
        
        if (nameInputLayout.error == null && loginInputLayout.error == null && passwordInputLayout.error == null && repeatedPasswordInputLayout.error == null) {
            val newUser = User(userName, userLogin, userPassword)
            usersRepository.addNewUser(newUser)
            
            Toast.makeText(this@RegistrationActivity, getString(R.string.text_registration_user_added_successfully), Toast.LENGTH_SHORT).show()
            finish()
        }
        
    }
    
    private fun onInputError(inputLayout: TextInputLayout, inputField: View, error: Error) {
        inputLayout.error = error.extract(this@RegistrationActivity)
    }
    
    companion object {
        private const val NECESSARY_PASSWORD_LENGTH = 8
    }
    
}
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
    
    private var haveSomeError = false
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        views = ActivityRegistrationBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
        }
        usersRepository = (applicationContext as UsersApplication).usersRepository
        
        views.apply {
            btnRegister.setOnClickListener(this@RegistrationActivity::register)
            
            // addTextChangedListener not worked with binding
            val etNameView : EditText = findViewById(R.id.et_name)
            val etLoginView : EditText = findViewById(R.id.et_login)
            val etPasswordView : EditText = findViewById(R.id.et_password)
            val etRepeatedPasswordView : EditText = findViewById(R.id.et_repeated_password)
            
            etNameView.addTextChangedListener(InputTextValidator(nameInputLayout, etName))
            etLoginView.addTextChangedListener(InputTextValidator(loginInputLayout, etLogin))
            etPasswordView.addTextChangedListener(InputTextValidator(passwordInputLayout, etPassword))
            etRepeatedPasswordView.addTextChangedListener(InputTextValidator(repeatedPasswordInputLayout, etRepeatedPassword))
        }
        
    }
    
    private fun changeErrorAvailability() {
        haveSomeError = true
    }
    
    private fun register(view: View) = with(views) {
        hideErrors()
        
        val userName = etName.text.toString()
        val userLogin = etLogin.text.toString()
        val userPassword = etPassword.text.toString()
        val repeatedUserPassword = etRepeatedPassword.text.toString()
        
        if (userName.isEmpty()) {
            changeErrorAvailability()
            onInputError(nameInputLayout, etName, Error.InputError.EmptyFieldError)
        }
        
        if (userLogin.isEmpty()) {
            changeErrorAvailability()
            onInputError(loginInputLayout, etLogin, Error.InputError.EmptyFieldError)
        }
        
        if (userPassword.length < NECESSARY_PASSWORD_LENGTH) {
            changeErrorAvailability()
            onInputError(passwordInputLayout, etPassword, Error.InputError.PasswordLengthError)
        }
        
        if (userPassword != repeatedUserPassword) {
            changeErrorAvailability()
            onInputError(repeatedPasswordInputLayout, etRepeatedPassword, Error.InputError.PasswordsDoNotMatchError)
        }
        
        if (!haveSomeError) {
            val newUser = User(userName, userLogin, userPassword)
    
            usersRepository.addNewUser(newUser)
    
            Toast.makeText(this@RegistrationActivity, getString(R.string.text_registration_user_added_successfully), Toast.LENGTH_SHORT).show()
    
            finish()
        }
        
        haveSomeError = false
        
    }
    
    private fun turnOnVirtualKeyboard(inputField: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(inputField, InputMethodManager.SHOW_IMPLICIT)
    }
    
    private fun onInputError(inputLayout: TextInputLayout, inputField: View, error: Error) = with(views) {
        turnOnVirtualKeyboard(inputField)
        
        inputLayout.error = error.extract(this@RegistrationActivity)
    }
    
    private fun hideErrors() = with(views) {
        nameInputLayout.error = null
        loginInputLayout.error = null
        passwordInputLayout.error = null
        repeatedPasswordInputLayout.error = null
    }
    
    companion object {
        private const val NECESSARY_PASSWORD_LENGTH = 8
    }
    
}
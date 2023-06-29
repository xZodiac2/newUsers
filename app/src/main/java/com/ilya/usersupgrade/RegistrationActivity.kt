package com.ilya.usersupgrade

import android.os.Bundle
import android.text.InputFilter.LengthFilter
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ilya.usersupgrade.databinding.ActivityRegistrationBinding

class RegistrationActivity : AppCompatActivity() {
    
    private lateinit var views: ActivityRegistrationBinding
    private lateinit var usersRepository: UsersRepository
    
    private val passwordInputValidator = PasswordInputValidator()
    private val inputValidator = InputValidator()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        views = ActivityRegistrationBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
        }
        usersRepository = (applicationContext as UsersApplication).usersRepository
        
        views.apply {
            
            btnRegister.setOnClickListener(this@RegistrationActivity::register)
            
            etName.filters += LengthFilter(nameInputLayout.counterMaxLength)
            etLogin.filters += LengthFilter(loginInputLayout.counterMaxLength)
            etPassword.filters += LengthFilter(passwordInputLayout.counterMaxLength)
            etRepeatedPassword.filters += LengthFilter(repeatedPasswordInputLayout.counterMaxLength)
            
        }
        
    }
    
    private fun register(view: View) = with(views) {
        if (inputFieldsIsCorrect()) {
            val userName = etName.text.toString()
            val userLogin = etLogin.text.toString()
            val userPassword = etPassword.text.toString()
            
            val newUser = User(userName, userLogin, userPassword)
            usersRepository.addNewUser(newUser)
            
            Toast.makeText(
                this@RegistrationActivity,
                getString(R.string.text_registration_user_added_successfully),
                Toast.LENGTH_SHORT
            ).show()
            
            finish()
        }
    }
    
    private fun inputFieldsIsCorrect(): Boolean = with(views) {
        nameInputLayout.error = if (!inputValidator.isFilled(etName.text.toString()))
            Error.InputError.EmptyFieldError.extract(this@RegistrationActivity)
        else
            null
        
        
        loginInputLayout.error = if (!inputValidator.isFilled(etLogin.text.toString()))
            Error.InputError.EmptyFieldError.extract(this@RegistrationActivity)
        else
            null
        
        passwordInputLayout.error = if (!passwordInputValidator.isNormalLength(etPassword.text.toString()))
            Error.InputError.PasswordLengthError.extract(this@RegistrationActivity)
        else
            null
        
        repeatedPasswordInputLayout.error = if (!passwordInputValidator.isPasswordsEquals(etPassword.text.toString(),etRepeatedPassword.text.toString()))
            Error.InputError.PasswordsDoNotMatchError.extract(this@RegistrationActivity)
        else if (!passwordInputValidator.isNormalLength(etRepeatedPassword.text.toString()))
            Error.InputError.PasswordLengthError.extract(this@RegistrationActivity)
        else
            null
        
        return (nameInputLayout.error == null &&
                loginInputLayout.error == null &&
                passwordInputLayout.error == null &&
                repeatedPasswordInputLayout.error == null)
        
    }
    
}
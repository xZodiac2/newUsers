package com.ilya.usersupgrade

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.ilya.usersupgrade.databinding.ActivityRegistrationBinding

class RegistrationActivity : AppCompatActivity() {
    
    private lateinit var views: ActivityRegistrationBinding
    private lateinit var application: UsersApplication
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        application = applicationContext as UsersApplication
        views = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(views.root)
        
        views.btnRegister.setOnClickListener(this::registration)
        
    }
    
    private fun onPasswordsError() = with(views) {
        tvErrorPasswords.visibility = View.VISIBLE
        tvErrorPasswords.text = Error.PasswordsDoNotMatchError.extract(this@RegistrationActivity)
        
        etPassword.setText("")
        etRepeatedPassword.setText("")
    }
    
    private fun registration(view: View) = with(views) {
        val userName = etUsername.text.toString()
        val userLogin = etLogin.text.toString()
        val userPassword = etPassword.text.toString()
        val userPasswordRepeated = etRepeatedPassword.text.toString()
        
        if (userPassword != userPasswordRepeated) {
            onPasswordsError()
            return
        }
        
        val user = User(userName, userLogin, userPassword)
    
        application.addNewUser(user)
        
        Toast.makeText(this@RegistrationActivity, getString(R.string.text_registration_successfully_user_adding), Toast.LENGTH_SHORT).show()
        
        finish()
        
    }
    
}
package com.ilya.usersupgrade

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
        val userName = etName.text.toString()
        val userLogin = etLogin.text.toString()
        val userPassword = etPassword.text.toString()
        
        val user = User(userName, userLogin, userPassword)
        
        usersRepository.addNewUser(user)
        
        Toast.makeText(this@RegistrationActivity, getString(R.string.text_registration_user_added_successfully), Toast.LENGTH_SHORT).show()
        
        finish()
    }
    
}
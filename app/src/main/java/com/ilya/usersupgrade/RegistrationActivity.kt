package com.ilya.usersupgrade

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.ilya.usersupgrade.databinding.ActivityRegistrationBinding

class RegistrationActivity : AppCompatActivity() {
    
    private lateinit var views: ActivityRegistrationBinding
    private lateinit var myApplication: MyApplication
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myApplication = applicationContext as MyApplication
        views = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(views.root)
        
        views.btnRegister.setOnClickListener(this::registration)
        
    }
    
    private fun registration(view: View) {
        var userName: String
        var userLogin: String
        var userPassword: String
        
        views.apply {
            userName = edUsername.text.toString()
            userLogin = edLogin.text.toString()
            userPassword = edLogin.text.toString()
        }
        
        val user = User(userName, userLogin, userPassword)
        
        myApplication.addNewUser(user)
        
        showAlertDialog()
        
    }
    
    private fun showAlertDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.text_registration_successfully))
        builder.setMessage(getString(R.string.text_registration_successfully_user_adding))
        builder.setPositiveButton(getString(R.string.text_registration_button_ok)) { _, _ -> finish() }
        builder.show()
    }
    
}
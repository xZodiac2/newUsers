package com.ilya.usersupgrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ilya.usersupgrade.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    
    private lateinit var activityMainViews: ActivityMainBinding
    private lateinit var application: UsersApplication
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainViews = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainViews.root)
    
        application = applicationContext as UsersApplication
    
        activityMainViews.btnLogin.setOnClickListener(this::login)
        activityMainViews.btnRegister.setOnClickListener { startActivity(Intent(this, RegistrationActivity::class.java)) }
    }

    private fun login(view: View) {
        authenticate()
            .onSuccess { user -> giveAccess(user) }
            .onFailure { error ->
                activityMainViews.tvError.visibility = View.VISIBLE
                activityMainViews.tvError.text = (error as Error).extract(this)
                clearInputFields()
            }
    }

    private fun authenticate(): Result<User> = with(activityMainViews) {
        val userLoginValue = etLoginInput.text.toString()
        val userPasswordValue = etPasswordInput.text.toString()

        return when (val foundUser = application.findUserByLoginAndPassword(userLoginValue, userPasswordValue)) {
            null -> Result.failure(Error.InvalidInputError)
            else -> Result.success(foundUser)
        }
    }

    private fun giveAccess(user: User) {
        val intent = Intent(this, UserGreetingActivity::class.java)
        intent.putExtra("userid", user.userId)
        startActivity(intent)
    }

    private fun clearInputFields() = with(activityMainViews) {
        etLoginInput.setText("")
        etPasswordInput.setText("")
    }
    
}
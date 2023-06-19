package com.ilya.usersupgrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ilya.usersupgrade.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    
    companion object {
        private const val KEY_FOR_SENDING_USER_ID = "userid"
    }
    
    private lateinit var activityMainViews: ActivityMainBinding
    private lateinit var myApplication: MyApplication
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainViews = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainViews.root)
    
        myApplication = applicationContext as MyApplication
    
        activityMainViews.btnLogin.setOnClickListener(this::login)
        activityMainViews.btnRegister.setOnClickListener { startActivity(Intent(this, RegistrationActivity::class.java)) }
    }

    private fun login(view: View) {
        authenticate()
            .onSuccess { user -> giveAccess(user) }
            .onFailure { error ->
                activityMainViews.tvError.visibility = View.VISIBLE
                mapError(error.message)
                clearInputFields()
            }
    }

    private fun authenticate(): Result<User> {
        val userLoginValue = activityMainViews.edLoginInput.text.toString()
        val userPasswordValue = activityMainViews.edPasswordInput.text.toString()

        return when (val foundUser = myApplication.findUserByLoginAndPassword(userLoginValue, userPasswordValue)) {
            null -> Result.failure(Error.InvalidInputError)
            else -> Result.success(foundUser)
        }
    }

    private fun giveAccess(user: User) {
        val intent = Intent(this, UserGreetingActivity::class.java)
        intent.putExtra(KEY_FOR_SENDING_USER_ID, user.userId)
        startActivity(intent)
    }

    private fun mapError(errorText: String?) {
        activityMainViews.tvError.text = errorText
    }

    private fun clearInputFields() {
        activityMainViews.edLoginInput.setText("")
        activityMainViews.edPasswordInput.setText("")
    }

}
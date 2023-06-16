package com.ilya.usersupgrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import com.ilya.usersupgrade.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainViews: ActivityMainBinding
    private lateinit var usersRepository: UsersRepository
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainViews = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainViews.root)
        
        usersRepository = applicationContext as UsersRepository
        activityMainViews.btnLogin.setOnClickListener(this::login)
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

        return when (val foundUser = usersRepository.findUserByLoginAndPassword(userLoginValue, userPasswordValue)) {
            null -> Result.failure(Error.InvalidInputError)
            else -> Result.success(foundUser)
        }
    }

    private fun giveAccess(user: User) {
        val intent = Intent(this, UserGreetingActivity::class.java)
        intent.putExtra("user id", user.id)
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
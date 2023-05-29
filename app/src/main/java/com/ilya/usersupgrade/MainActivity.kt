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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainViews = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainViews.root)

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

        return when (val foundUser = findUser(userLoginValue, userPasswordValue)) {
            null -> Result.failure(Error.InvalidInputError)
            else -> Result.success(foundUser)
        }
    }

    private fun findUser(login: String, password: String): User? {
        return users.find { user ->  user.login == login && user.password == password}
    }

    private fun giveAccess(user: User) {
        user.haveAccess = true

        val intent = Intent(this, UserGreetingActivity::class.java)
        startActivity(intent)
    }

    private fun mapError(errorText: String?) {
        activityMainViews.tvError.text = errorText
    }

    private fun clearInputFields() {
        activityMainViews.edLoginInput.setText("")
        activityMainViews.edPasswordInput.setText("")
    }

    override fun onRestart() {
        super.onRestart()

        clearInputFields()
        activityMainViews.tvError.visibility = View.GONE
    }

}
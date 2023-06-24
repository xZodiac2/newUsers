package com.ilya.usersupgrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ilya.usersupgrade.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    
    private lateinit var activityMainViews: ActivityMainBinding
    private lateinit var usersRepository: UsersRepository
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainViews = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainViews.root)
    
        usersRepository = (applicationContext as UsersApplication).usersRepository
    
        activityMainViews.apply {
            btnLogin.setOnClickListener(this@MainActivity::login)
            btnOfferToRegister.setOnClickListener { startActivity(Intent(this@MainActivity, RegistrationActivity::class.java)) }
        }
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

        return when (val foundUser = usersRepository.findUserByLoginAndPassword(userLoginValue, userPasswordValue)) {
            null -> Result.failure(Error.InvalidInputError)
            else -> Result.success(foundUser)
        }
    }

    private fun giveAccess(user: User) {
        val intent = Intent(this, UserGreetingActivity::class.java)
        intent.putExtra(UserGreetingActivity.KEY_USER_ID, user.userId)
        startActivity(intent)
    }

    private fun clearInputFields() = with(activityMainViews) {
        etLoginInput.setText("")
        etPasswordInput.setText("")
    }
    
}
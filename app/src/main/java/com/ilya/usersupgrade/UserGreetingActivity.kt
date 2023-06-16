package com.ilya.usersupgrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ilya.usersupgrade.databinding.ActivityUserGreetingBinding

class UserGreetingActivity : AppCompatActivity() {

    private lateinit var usersRepository: UsersRepository
    private lateinit var activityUserGreetingViews: ActivityUserGreetingBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityUserGreetingViews = ActivityUserGreetingBinding.inflate(layoutInflater)
        setContentView(activityUserGreetingViews.root)
        
        usersRepository = applicationContext as UsersRepository
    
        when (val user = usersRepository.findUserById(intent?.getIntExtra("user id", -1))) {
            null -> finish()
            else -> {
                activityUserGreetingViews.tvGreeting.text = "${getString(R.string.text_greeting)} ${user.name}"
            }
        }

        activityUserGreetingViews.btnUnlogin.setOnClickListener(this::logout)

    }
    
    private fun logout(view: View) {
        finish()
    }

}
package com.ilya.usersupgrade

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ilya.usersupgrade.databinding.ActivityUserGreetingBinding

class UserGreetingActivity : AppCompatActivity() {
    
    private lateinit var application: UsersApplication
    private lateinit var activityUserGreetingViews: ActivityUserGreetingBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityUserGreetingViews = ActivityUserGreetingBinding.inflate(layoutInflater)
        setContentView(activityUserGreetingViews.root)
    
        application = applicationContext as UsersApplication
        
        val userId = intent.getIntExtra(KEY_USER_ID, USER_NOT_FOUND_ID)
        
        if (userId == -1) {
            finish()
        }
        
        when (val user = application.usersRepository.findUserById(userId)) {
            null -> finish()
            else -> activityUserGreetingViews.tvGreeting.text = "${getString(R.string.text_greeting)} ${user.name}"
        }

        activityUserGreetingViews.btnUnlogin.setOnClickListener(this::logout)

    }
    
    private fun logout(view: View) {
        finish()
    }
    
    companion object {
        private const val USER_NOT_FOUND_ID = -1
        const val KEY_USER_ID = "userId"
    }
    
}
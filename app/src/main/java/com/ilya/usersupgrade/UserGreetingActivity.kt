package com.ilya.usersupgrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ilya.usersupgrade.databinding.ActivityUserGreetingBinding

class UserGreetingActivity : AppCompatActivity() {
    
    companion object {
        private const val KEY_FOR_GETTING_USER_ID = "userid"
    }
    
    private lateinit var usersRepository: MyApplication
    private lateinit var activityUserGreetingViews: ActivityUserGreetingBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityUserGreetingViews = ActivityUserGreetingBinding.inflate(layoutInflater)
        setContentView(activityUserGreetingViews.root)
        
        usersRepository = applicationContext as MyApplication
        
        val userId = intent?.getIntExtra(KEY_FOR_GETTING_USER_ID, -1)
        
        if (userId == null || userId == -1) {
            finish()
        }
        
        when (val user = usersRepository.findUserById(userId)) {
            null -> finish()
            else -> activityUserGreetingViews.tvGreeting.text = "${getString(R.string.text_greeting)} ${user.name}"
        }

        activityUserGreetingViews.btnUnlogin.setOnClickListener(this::logout)

    }
    
    private fun logout(view: View) {
        finish()
    }
    
}
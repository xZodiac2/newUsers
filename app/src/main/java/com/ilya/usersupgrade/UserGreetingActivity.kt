package com.ilya.usersupgrade

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ilya.usersupgrade.databinding.ActivityUserGreetingBinding

class UserGreetingActivity : AppCompatActivity() {

    private lateinit var activityUserGreeting: ActivityUserGreetingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityUserGreeting = ActivityUserGreetingBinding.inflate(layoutInflater)
        setContentView(activityUserGreeting.root)

        val userName = intent.getStringExtra("userName")
        activityUserGreeting.tvGreeting.text = "${activityUserGreeting.tvGreeting.text} $userName"

        activityUserGreeting.btnUnlogin.setOnClickListener(this::unlogin)
    }

    private fun unlogin(view: View) {
        finish()
    }

}
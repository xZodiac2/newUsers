package com.ilya.usersupgrade

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ilya.usersupgrade.databinding.ActivityUserGreetingBinding

class UserGreetingActivity : AppCompatActivity() {

    private lateinit var activityUserGreetingViews: ActivityUserGreetingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityUserGreetingViews = ActivityUserGreetingBinding.inflate(layoutInflater)
        setContentView(activityUserGreetingViews.root)

        val userName = intent.getStringExtra("userName")
        activityUserGreetingViews.tvGreeting.text = "${activityUserGreetingViews.tvGreeting.text} $userName"

        activityUserGreetingViews.btnUnlogin.setOnClickListener(this::unlogin)
    }

    private fun unlogin(view: View) {
        finish()
    }

}
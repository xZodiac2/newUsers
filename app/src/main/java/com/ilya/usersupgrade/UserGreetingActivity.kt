package com.ilya.usersupgrade

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ilya.usersupgrade.databinding.ActivityUserGreetingBinding

class UserGreetingActivity : AppCompatActivity() {

    private lateinit var activityUserGreetingViews: ActivityUserGreetingBinding
    private lateinit var nameOfSignedUser: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityUserGreetingViews = ActivityUserGreetingBinding.inflate(layoutInflater)
        setContentView(activityUserGreetingViews.root)

        nameOfSignedUser = intent.getStringExtra("userName") ?: "undefined"

        when (nameOfSignedUser) {
            "undefined" -> {
                toUIWithError()
                mapError(Error.UserNameIsUndefined.message)
            }
            else -> activityUserGreetingViews.tvGreeting.text = "${activityUserGreetingViews.tvGreeting.text} $nameOfSignedUser"
        }

        activityUserGreetingViews.btnUnlogin.setOnClickListener(this::unlogin)
    }

    private fun mapError(error: String) {
        activityUserGreetingViews.tvErrorUserIsNull.text = error
    }

    private fun toUIWithError() {
        activityUserGreetingViews.tvErrorUserIsNull.show()
        activityUserGreetingViews.tvGreeting.hide()
    }

    private fun toDefaultUI() {
        activityUserGreetingViews.tvGreeting.show()
        activityUserGreetingViews.tvErrorUserIsNull.hide()
    }


    private fun unlogin(view: View) {
        finish()
    }

    override fun onRestart() {
        super.onRestart()

        toDefaultUI()
    }

}
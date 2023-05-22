package com.ilya.usersupgrade

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
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
            else -> {
                toDefaultUI()
                mapUserNameToUI()
            }
        }

        activityUserGreetingViews.btnUnlogin.setOnClickListener(this::logout)
    }

    private fun mapUserNameToUI() {
        activityUserGreetingViews.tvGreeting.text = "${getString(R.string.text_greeting)} $nameOfSignedUser"
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


    private fun logout(view: View) {
        finish()
    }

    override fun onRestart() {
        super.onRestart()

        toDefaultUI()
    }

}
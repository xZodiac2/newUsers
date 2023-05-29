package com.ilya.usersupgrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ilya.usersupgrade.databinding.ActivityUserGreetingBinding

class UserGreetingActivity : AppCompatActivity() {

    private lateinit var activityUserGreetingViews: ActivityUserGreetingBinding
    private val signedUser = users.find { userData -> userData.haveAccess}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityUserGreetingViews = ActivityUserGreetingBinding.inflate(layoutInflater)
        setContentView(activityUserGreetingViews.root)

        when (signedUser) {
            null -> toUiWithError()
            else -> toUserGreetingUI()
        }

        activityUserGreetingViews.btnUnlogin.setOnClickListener(this::logout)

    }

    private fun mapErrorToUI(errorMessage: String) {
        activityUserGreetingViews.tvErrorUserIsNull.text = errorMessage
    }

    private fun removeErrorFromUI() {
        activityUserGreetingViews.tvErrorUserIsNull.text = ""
    }

    private fun mapUserNameToUI() {
        if (signedUser == null) {
            toUiWithError()
            mapErrorToUI(Error.UserIsNull.message)
            return
        }

        activityUserGreetingViews.tvGreeting.text = "${getString(R.string.text_greeting)} ${signedUser.name}"
    }

    private fun removeUserNameFromUI() {
        activityUserGreetingViews.tvGreeting.text = getString(R.string.text_greeting)
    }

    private fun toUiWithError() = with(activityUserGreetingViews) {
        tvGreeting.hide()
        removeUserNameFromUI()

        tvErrorUserIsNull.show()
        mapErrorToUI(Error.UserIsNull.message)
    }

    private fun toUserGreetingUI() = with(activityUserGreetingViews) {
        tvGreeting.show()
        mapUserNameToUI()

        tvErrorUserIsNull.hide()
        removeErrorFromUI()
    }

    private fun toDefaultUI() = with(activityUserGreetingViews) {
        tvErrorUserIsNull.hide()
        removeErrorFromUI()

        tvGreeting.show()
        removeUserNameFromUI()
    }

    private fun logout(view: View) {
        finish()
    }

    override fun onRestart() {
        super.onRestart()

        toDefaultUI()
    }

}
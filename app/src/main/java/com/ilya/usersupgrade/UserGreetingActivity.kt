package com.ilya.usersupgrade

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.View
import com.ilya.usersupgrade.databinding.ActivityUserGreetingBinding

class UserGreetingActivity : AppCompatActivity() {

    private lateinit var activityUserGreetingViews: ActivityUserGreetingBinding

    private var signedUserDataArray: Array<String>? = null
    private lateinit var signedUser: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityUserGreetingViews = ActivityUserGreetingBinding.inflate(layoutInflater)
        setContentView(activityUserGreetingViews.root)

        signedUserDataArray = intent.getStringArrayExtra("userData")

        when (signedUserDataArray) {
            null -> {
                toUIWithError()
                mapError(Error.UserNameIsNull.message)
            }
            else -> {
                val (userLoginValue, userPasswordValue) = signedUserDataArray!! // signedUserDataArray never be null here
                signedUser = users.find { userData -> userData.login == userLoginValue && userData.password == userPasswordValue }!! // here .find method always will return some user
                toDefaultUI()
                mapUserNameToUI()
            }
        }

        activityUserGreetingViews.btnUnlogin.setOnClickListener(this::logout)
    }

    private fun mapUserNameToUI() {
        activityUserGreetingViews.tvGreeting.text = "${getString(R.string.text_greeting)} ${signedUser.name}"
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
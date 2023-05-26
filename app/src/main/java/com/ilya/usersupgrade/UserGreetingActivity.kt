package com.ilya.usersupgrade

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ilya.usersupgrade.databinding.ActivityUserGreetingBinding

class UserGreetingActivity : AppCompatActivity() {

    private lateinit var activityUserGreetingViews: ActivityUserGreetingBinding

    private lateinit var signedUserDataArray: Array<String>
    private var signedUser: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityUserGreetingViews = ActivityUserGreetingBinding.inflate(layoutInflater)
        setContentView(activityUserGreetingViews.root)

        signedUserDataArray = intent.getStringArrayExtra("userData") ?: arrayOf("null")

        if (signedUserDataArray[0] == "null" && signedUserDataArray.size == 1) {
            toUIWithError()
            mapError(Error.UserIsNull.message)
        } else {
            val (userLoginValue, userPasswordValue) = signedUserDataArray
            signedUser = users.find { userData -> userData.login == userLoginValue && userData.password == userPasswordValue }
            toDefaultUI()
            mapUserDataToUI()
        }


        activityUserGreetingViews.btnUnlogin.setOnClickListener(this::logout)
    }

    private fun mapUserDataToUI() {
        if (signedUser == null) return mapError(Error.UserIsNull.message)

        activityUserGreetingViews.tvGreeting.text = "${getString(R.string.text_greeting)} ${signedUser?.name}"
    }

    private fun mapError(error: String) {
        activityUserGreetingViews.tvErrorUserIsNull.text = error
    }

    private fun toUIWithError() {
        activityUserGreetingViews.tvErrorUserIsNull.show()
        activityUserGreetingViews.tvGreeting.hide()
    }

    private fun toDefaultUI() {
        if (signedUser == null) return mapError(Error.UserIsNull.message)

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
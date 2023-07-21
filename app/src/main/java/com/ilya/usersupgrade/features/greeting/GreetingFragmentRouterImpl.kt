package com.ilya.usersupgrade.features.greeting

import androidx.navigation.NavController
import com.ilya.greeting.GreetingFragmentDirections
import com.ilya.greeting.navigation.GreetingFragmentRouter
import javax.inject.Inject

class GreetingFragmentRouterImpl @Inject constructor(
    private val navController: NavController
) : GreetingFragmentRouter {
    override fun backToLogin() {
        navController.navigate(GreetingFragmentDirections.actionUserGreetingFragmentToLoginFragment())
    }
}
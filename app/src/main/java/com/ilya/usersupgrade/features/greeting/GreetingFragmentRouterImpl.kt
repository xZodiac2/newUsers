package com.ilya.usersupgrade.features.greeting

import androidx.navigation.NavController
import com.ilya.presentation.GreetingFragmentDirections
import com.ilya.presentation.navigation.GreetingFragmentRouter
import javax.inject.Inject

class GreetingFragmentRouterImpl @Inject constructor(
    private val navController: NavController
) : GreetingFragmentRouter {
    override fun backToLogin() {
        navController.navigate(GreetingFragmentDirections.actionGreetingFragmentToLoginFragment())
    }
}
package com.ilya.usersupgrade.featureNav.greeting

import androidx.navigation.NavController
import com.ilya.greeting.presentation.GreetingFragmentDirections
import com.ilya.greeting.presentation.navigation.GreetingFragmentRouter
import javax.inject.Inject

class GreetingFragmentRouterImpl @Inject constructor(
    private val navController: NavController
): GreetingFragmentRouter {
    
    override fun backToLogin() {
        navController.navigate(GreetingFragmentDirections.actionGreetingFragmentToLoginFragment())
    }
}
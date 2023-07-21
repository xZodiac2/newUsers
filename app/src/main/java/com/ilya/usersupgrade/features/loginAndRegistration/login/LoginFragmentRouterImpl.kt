package com.ilya.usersupgrade.features.loginAndRegistration.login

import androidx.navigation.NavController
import com.ilya.loginandregistration.login.LoginFragmentDirections
import com.ilya.loginandregistration.login.navigation.LoginFragmentRouter
import javax.inject.Inject

class LoginFragmentRouterImpl @Inject constructor(
    private val navController: NavController
) : LoginFragmentRouter {
    override fun goToGreeting(userId: Int) {
        navController.navigate(LoginFragmentDirections.actionLoginFragmentToUserGreetingFragment(userId))
    }
    
    override fun goToRegistration() {
        navController.navigate(LoginFragmentDirections.actionLoginFragmentToRegistrationFragment())
    }
}
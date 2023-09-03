package com.ilya.usersupgrade.featureNav.loginAndRegistration.login

import androidx.navigation.NavController
import com.ilya.loginandregistration.login.presentation.LoginFragmentDirections
import com.ilya.loginandregistration.login.presentation.navigation.LoginFragmentRouter
import javax.inject.Inject

class LoginFragmentRouterImpl @Inject constructor(
    private val navController: NavController
) : LoginFragmentRouter {
    
    override fun goToRegistration() {
        navController.navigate(LoginFragmentDirections.actionLoginFragmentToRegistrationFragment())
    }
    
    override fun goToGreeting(login: String) {
        navController.navigate(LoginFragmentDirections.actionLoginFragmentToGreetingFragment(login))
    }
}
package com.ilya.usersupgrade.featureNav.loginAndRegistration.registration

import androidx.navigation.NavController
import com.ilya.loginandregistration.registration.presentation.RegistrationFragmentDirections
import com.ilya.loginandregistration.registration.presentation.navigation.RegistrationFragmentRouter
import javax.inject.Inject

class RegistrationFragmentRouterImpl @Inject constructor(
    private val navController: NavController
) : RegistrationFragmentRouter {
    
    override fun backToLogin() {
        navController.navigate(RegistrationFragmentDirections.actionRegistrationFragmentToLoginFragment())
    }
}
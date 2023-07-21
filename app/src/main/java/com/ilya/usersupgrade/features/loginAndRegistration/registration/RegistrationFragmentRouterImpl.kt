package com.ilya.usersupgrade.features.loginAndRegistration.registration

import androidx.navigation.NavController
import com.ilya.loginandregistration.registration.RegistrationFragmentDirections
import com.ilya.loginandregistration.registration.navigation.RegistrationFragmentRouter
import javax.inject.Inject

class RegistrationFragmentRouterImpl @Inject constructor(
    private val navController: NavController
) : RegistrationFragmentRouter {
    override fun backToLogin() {
        navController.navigate(RegistrationFragmentDirections.actionRegistrationFragmentToLoginFragment())
    }
}
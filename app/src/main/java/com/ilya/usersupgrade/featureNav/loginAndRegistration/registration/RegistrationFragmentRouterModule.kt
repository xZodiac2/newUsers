package com.ilya.usersupgrade.featureNav.loginAndRegistration.registration

import com.ilya.loginandregistration.registration.presentation.navigation.RegistrationFragmentRouter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
interface RegistrationFragmentRouterModule {
    @Binds
    fun bindRegistrationFragmentRouterImpl(registrationFragmentRouterImpl: RegistrationFragmentRouterImpl): RegistrationFragmentRouter
}
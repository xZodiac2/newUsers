package com.ilya.usersupgrade.features.loginAndRegistration.registration

import com.ilya.loginandregistration.registration.navigation.RegistrationFragmentRouter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface RegistrationFragmentRouterModule {
    @Binds
    fun bindRegistrationFragment(registrationFragmentRouterImpl: RegistrationFragmentRouterImpl): RegistrationFragmentRouter
}
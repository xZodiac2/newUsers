package com.ilya.usersupgrade.features.loginAndRegistration.registration

import com.ilya.presentation.registration.navigation.RegistrationFragmentRouter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface RegistrationFragmentRouterModule {
    @Binds
    fun bindRegistrationFragmentRouter(registrationFragmentRouterImpl: RegistrationFragmentRouterImpl): RegistrationFragmentRouter
}
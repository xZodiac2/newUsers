package com.ilya.usersupgrade.features.loginAndRegistration.registration

import com.ilya.loginandregistration.registration.presentation.navigation.RegistrationFragmentRouter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RegistrationFragmentModule {
    @Binds
    fun bindRegistrationFragmentRouter(registrationFragmentRouterImpl: RegistrationFragmentRouterImpl): RegistrationFragmentRouter
}
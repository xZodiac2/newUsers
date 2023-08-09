package com.ilya.usersupgrade.featureNav.loginAndRegistration.login

import com.ilya.loginandregistration.login.presentation.navigation.LoginFragmentRouter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
interface LoginFragmentRouterModule {
    @Binds fun bindLoginFragmentRouterImpl(loginFragmentRouterImpl: LoginFragmentRouterImpl): LoginFragmentRouter
}
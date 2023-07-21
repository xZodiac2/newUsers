package com.ilya.usersupgrade.features.loginAndRegistration.login

import com.ilya.loginandregistration.login.navigation.LoginFragmentRouter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface LoginFragmentRouterModule {
    @Binds
    fun bindLoginFragmentRouterModule(loginFragmentRouterImpl: LoginFragmentRouterImpl): LoginFragmentRouter
}
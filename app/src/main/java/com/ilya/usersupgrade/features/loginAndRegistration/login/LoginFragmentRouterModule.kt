package com.ilya.usersupgrade.features.loginAndRegistration.login

import com.ilya.loginandregistration.login.presentation.navigation.LoginFragmentRouter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
interface LoginFragmentRouterModule {
    @Binds
    fun bindLoginFragmentRouter(loginFragmentRouterImpl: LoginFragmentRouterImpl): LoginFragmentRouter
}

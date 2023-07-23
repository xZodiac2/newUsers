package com.ilya.usersupgrade.features.greeting

import com.ilya.presentation.navigation.GreetingFragmentRouter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface GreetingFragmentRouterModule {
    @Binds
    fun bindGreetingFragmentRouter(greetingFragmentRouterImpl: GreetingFragmentRouterImpl): GreetingFragmentRouter
}
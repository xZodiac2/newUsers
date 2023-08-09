package com.ilya.usersupgrade.featureNav.greeting

import com.ilya.greeting.presentation.navigation.GreetingFragmentRouter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
interface GreetingFragmentRouterModule {
    @Binds fun bindGreetingFragmentRouterImpl(greetingFragmentRouterImpl: GreetingFragmentRouterImpl): GreetingFragmentRouter
}
package com.ilya.usersupgrade.features.greeting

import com.ilya.greeting.presentation.navigation.GreetingFragmentRouter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface GreetingFragmentRouterModule {
    @Binds
    fun bindGreetingFragmentRouterImpl(greetingFragmentRouterImpl: GreetingFragmentRouterImpl): GreetingFragmentRouter
}
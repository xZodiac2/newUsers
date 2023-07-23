package com.ilya.usersupgrade.features.common

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.ilya.usersupgrade.MainActivity
import com.ilya.usersupgrade.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object NavControllerProvider {
    @Provides
    fun provideNavController(): NavController {
        return (MainActivity.activity.supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
    }
}
package com.ilya.usersupgrade.featureNav.common

import android.content.Context
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.ilya.usersupgrade.MainActivity
import com.ilya.usersupgrade.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(ActivityComponent::class)
object NavControllerProvider {
    
    @Provides
    fun provideNavController(@ActivityContext context: Context): NavController {
        return ((context as MainActivity).supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
    }
}
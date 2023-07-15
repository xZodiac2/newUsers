package com.ilya.usersupgrade

import android.content.Context
import android.view.Choreographer.FrameCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

fun Error.extract(context: Context): String {
    return context.getString(stringId)
}

fun Fragment.getNavController(): NavController {
    return (requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
}

fun Fragment.getUsersRepository(): UsersRepository {
    return (requireActivity().applicationContext as UsersApplication).usersRepository
}
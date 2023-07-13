package com.ilya.usersupgrade

import android.app.Activity
import android.app.Application
import androidx.navigation.NavController
import androidx.navigation.Navigation

class UsersApplication : Application() {
    
    lateinit var usersRepository: UsersRepository
        private set
    
    override fun onCreate() {
        super.onCreate()
        usersRepository = UsersRepository()
    }
    
}
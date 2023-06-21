package com.ilya.usersupgrade

import android.app.Application

class UsersApplication : Application() {
    
    lateinit var usersRepository: UsersRepository
        private set
    
    override fun onCreate() {
        super.onCreate()
        usersRepository = UsersRepository()
    }
    
}
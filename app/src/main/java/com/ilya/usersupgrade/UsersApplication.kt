package com.ilya.usersupgrade

import android.app.Application

class UsersApplication : Application() {
   
   lateinit var usersRepository: UsersRepository
   override fun onCreate() {
      super.onCreate()
      usersRepository = UsersRepository()
   }
    
}
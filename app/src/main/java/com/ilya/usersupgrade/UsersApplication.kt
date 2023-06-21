package com.ilya.usersupgrade

import android.app.Application

class UsersApplication : Application() {
    
   val usersRepository = UsersRepository()
    
}
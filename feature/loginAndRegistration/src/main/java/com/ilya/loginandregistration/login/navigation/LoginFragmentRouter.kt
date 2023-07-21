package com.ilya.loginandregistration.login.navigation

interface LoginFragmentRouter {
    fun goToRegistration()
    fun goToGreeting(userId: Int)
}
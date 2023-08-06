package com.ilya.loginandregistration.login.presentation.navigation

interface LoginFragmentRouter {
    fun goToGreeting(userId: Int)
    fun goToRegistration()
}
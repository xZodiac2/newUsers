package com.ilya.presentation.login.navigation

interface LoginFragmentRouter {
    fun goToRegistration()
    fun goToGreeting(userId: Int)
}
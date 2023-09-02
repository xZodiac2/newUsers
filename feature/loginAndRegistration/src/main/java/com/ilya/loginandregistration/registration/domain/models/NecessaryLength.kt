package com.ilya.loginandregistration.registration.domain.models

sealed interface NecessaryLength {
    val value: Int
    
    data class Login(override val value: Int) : NecessaryLength
    data class Password(override val value: Int) : NecessaryLength
}
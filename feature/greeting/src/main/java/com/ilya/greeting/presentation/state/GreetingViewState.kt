package com.ilya.greeting.presentation.state

import com.ilya.core.TextReference
import com.ilya.greeting.domain.models.GreetingUserData


data class GreetingViewState(
    val greetingTextReference: TextReference,
    val user: GreetingUserData? = null,
    val contentVisibility: Int,
    val progressBarVisibility: Int
)
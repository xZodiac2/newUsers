package com.ilya.greeting.presentation.state

import com.ilya.core.TextReference
import com.ilya.core.enums.ViewVisibility
import com.ilya.greeting.R
import com.ilya.greeting.domain.models.GreetingUserData


data class GreetingViewState(
    val greetingTextReference: TextReference = TextReference.Resource(R.string.text_greeting, listOf("")),
    val user: GreetingUserData? = null,
    val userNameVisibility: ViewVisibility = ViewVisibility.GONE,
    val progressBarVisibility: ViewVisibility = ViewVisibility.VISIBLE,
)
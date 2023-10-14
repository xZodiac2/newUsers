package com.ilya.greeting.presentation.view

import com.ilya.core.getStringByReference
import com.ilya.core.setViewVisibility
import com.ilya.greeting.databinding.FragmentGreetingBinding
import com.ilya.greeting.presentation.callback.GreetingViewCallback
import com.ilya.greeting.presentation.state.GreetingScreenState

class GreetingView(
    private val binding: FragmentGreetingBinding,
    private val callback: GreetingViewCallback,
) {
    
    init {
        initViews()
    }
    
    private fun initViews() = with(binding) {
        btnLogout.setOnClickListener { callback.onLogoutClick() }
    }
    
    fun bind(greetingScreenState: GreetingScreenState) = with(binding) {
        tvName.setViewVisibility(greetingScreenState.userNameVisibility)
        progressBar.setViewVisibility(greetingScreenState.progressBarVisibility)
        tvName.text = greetingScreenState.greetingTextReference?.let {
            root.context.getStringByReference(it)
        }
    }
    
}
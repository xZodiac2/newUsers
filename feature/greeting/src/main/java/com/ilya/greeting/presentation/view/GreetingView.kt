package com.ilya.greeting.presentation.view

import com.ilya.core.getStringByReference
import com.ilya.core.setViewVisibility
import com.ilya.greeting.databinding.FragmentGreetingBinding
import com.ilya.greeting.presentation.callback.GreetingViewCallback
import com.ilya.greeting.presentation.state.GreetingViewState

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
    
    fun bind(greetingViewState: GreetingViewState) = with(binding) {
        tvName.setViewVisibility(greetingViewState.userNameVisibility)
        progressBar.setViewVisibility(greetingViewState.progressBarVisibility)
        tvName.text = greetingViewState.greetingTextReference?.let {
            root.context.getStringByReference(it)
        }
    }
    
}
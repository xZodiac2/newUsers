package com.ilya.greeting.presentation.view

import com.ilya.core.setTextByReference
import com.ilya.greeting.databinding.FragmentGreetingBinding
import com.ilya.greeting.presentation.callback.GreetingViewCallback
import com.ilya.greeting.presentation.state.GreetingViewState

class GreetingView(
    private val binding: FragmentGreetingBinding,
    private val callback: GreetingViewCallback
) {

    init {
        initViews()
    }
    
    private fun initViews() = with(binding) {
        btnLogout.setOnClickListener { callback.onLogoutClick() }
    }
    
    fun bind(greetingViewState: GreetingViewState?) {
        greetingViewState ?: return
     
        binding.tvName.setTextByReference(greetingViewState.greetingTextReference)
    }
    
}
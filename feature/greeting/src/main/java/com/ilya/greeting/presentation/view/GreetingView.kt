package com.ilya.greeting.presentation.view

import androidx.core.view.iterator
import com.ilya.core.setTextByReference
import com.ilya.core.setViewVisibility
import com.ilya.greeting.R
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
     
        for (view in binding.root) {
            if (view.id == R.id.progress_bar) {
                view.setViewVisibility(greetingViewState.progressBarVisibility)
                continue
            }
            view.setViewVisibility(greetingViewState.contentVisibility)
        }
        
        binding.tvName.setTextByReference(greetingViewState.greetingTextReference)
    }
    
}
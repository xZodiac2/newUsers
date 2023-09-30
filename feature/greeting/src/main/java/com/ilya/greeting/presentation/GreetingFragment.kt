package com.ilya.greeting.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ilya.greeting.databinding.FragmentGreetingBinding
import com.ilya.greeting.presentation.navigation.GreetingFragmentRouter
import com.ilya.greeting.presentation.view.GreetingView
import com.ilya.greeting.presentation.viewModel.GreetingViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GreetingFragment : Fragment() {
    
    private lateinit var binding: FragmentGreetingBinding
    private lateinit var view: GreetingView
    
    private val greetingViewModel: GreetingViewModel by viewModels()
    
    @Inject
    lateinit var greetingFragmentRouter: GreetingFragmentRouter
    
    override fun onAttach(context: Context) {
        super.onAttach(context)
        
        greetingViewModel.greetingFragmentRouter = greetingFragmentRouter
    }
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentGreetingBinding.inflate(inflater, container, false)
        view = GreetingView(binding, greetingViewModel)
        lifecycleScope.launchWhenStarted { greetingViewModel.screenStateFlow.collect(view::bind) }
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        greetingViewModel.getUser(requireArguments())
    }
    
}
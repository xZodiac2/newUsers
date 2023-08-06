package com.ilya.greeting.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ilya.greeting.databinding.FragmentGreetingBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GreetingFragment : Fragment() {
    
    private lateinit var binding: FragmentGreetingBinding
    
    private val greetingViewModel: GreetingViewModel by viewModels()
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentGreetingBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.viewModel = greetingViewModel
        binding.lifecycleOwner = this
        
        greetingViewModel.findUserAndMapHisName(requireArguments(), requireContext())
        
        binding.btnLogout.setOnClickListener { greetingViewModel.backToLogin() }
        
    }
    
   
    
}
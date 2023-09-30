package com.ilya.loginandregistration.registration.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ilya.loginandregistration.databinding.FragmentRegistrationBinding
import com.ilya.loginandregistration.registration.presentation.navigation.RegistrationFragmentRouter
import com.ilya.loginandregistration.registration.presentation.veiwModel.RegistrationViewModel
import com.ilya.loginandregistration.registration.presentation.view.RegistrationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegistrationFragment : Fragment() {
    
    private lateinit var binding: FragmentRegistrationBinding
    private lateinit var view: RegistrationView
    
    private val registrationViewModel: RegistrationViewModel by viewModels()
    
    @Inject
    lateinit var registrationFragmentRouter: RegistrationFragmentRouter
    
    override fun onAttach(context: Context) {
        super.onAttach(context)
        
        registrationViewModel.registrationFragmentRouter = registrationFragmentRouter
    }
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        view = RegistrationView(binding, registrationViewModel)
        lifecycleScope.launchWhenStarted { registrationViewModel.screenStateFlow.collect(view::bindScreenState) }
        lifecycleScope.launchWhenStarted { registrationViewModel.userRegistrationStatusSharedFlow.collect(view::bindRegistrationStatus) }
        return binding.root
    }
    
}
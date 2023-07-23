package com.ilya.presentation.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ilya.presentation.databinding.FragmentRegistrationBinding
import com.ilya.presentation.registration.navigation.RegistrationFragmentRouter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegistrationFragment : Fragment() {
    
    @Inject lateinit var registrationFragmentRouter: RegistrationFragmentRouter
    
    private lateinit var binding: FragmentRegistrationBinding
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.btnRegister.setOnClickListener {
            registrationFragmentRouter.backToLogin()
        }
    }
   
}
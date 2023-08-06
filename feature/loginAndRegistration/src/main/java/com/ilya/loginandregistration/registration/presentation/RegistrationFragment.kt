package com.ilya.loginandregistration.registration.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ilya.loginandregistration.databinding.FragmentRegistrationBinding
import com.ilya.loginandregistration.registration.domain.models.InputFieldsValues
import com.ilya.loginandregistration.registration.presentation.models.InputFieldsLayouts
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationFragment : Fragment() {
    
    private lateinit var binding: FragmentRegistrationBinding
    
    private val registrationViewModel: RegistrationViewModel by viewModels()
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.btnRegister.setOnClickListener(::register)
        
    }
    
    private fun register(view: View) = with(binding) {
        val name = etName.text.toString()
        val login = etLogin.text.toString()
        val password = etPassword.text.toString()
        val repeatedPassword = etRepeatedPassword.text.toString()
        
        val inputFieldsValues = InputFieldsValues(name, login, password, repeatedPassword)
        val inputFieldsLayouts = InputFieldsLayouts(nameInputLayout, loginInputLayout, passwordInputLayout, repeatedPasswordInputLayout)
        
        registrationViewModel.registerNewUser(inputFieldsValues, inputFieldsLayouts, requireContext())
        
    }
    
}
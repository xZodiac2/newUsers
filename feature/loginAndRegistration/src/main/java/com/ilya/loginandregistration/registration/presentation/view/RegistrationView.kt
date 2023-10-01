package com.ilya.loginandregistration.registration.presentation.view

import android.text.InputFilter.LengthFilter
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.ilya.core.getStringByReference
import com.ilya.core.setViewVisibility
import com.ilya.loginandregistration.R
import com.ilya.loginandregistration.databinding.FragmentRegistrationBinding
import com.ilya.loginandregistration.registration.presentation.callback.RegistrationViewCallback
import com.ilya.loginandregistration.registration.presentation.error.PresentationErrorList
import com.ilya.loginandregistration.registration.presentation.models.InputFieldValues
import com.ilya.loginandregistration.registration.presentation.state.RegistrationScreenState

class RegistrationView(
    private val binding: FragmentRegistrationBinding,
    private val callback: RegistrationViewCallback,
) {
    
    init {
        initViews()
    }
    
    private val context = binding.root.context
    
    private fun initViews() = with(binding) {
        etName.filters += LengthFilter(nameInputLayout.counterMaxLength)
        etLogin.filters += LengthFilter(loginInputLayout.counterMaxLength)
        etPassword.filters += LengthFilter(passwordInputLayout.counterMaxLength)
        etRepeatedPassword.filters += LengthFilter(repeatedPasswordInputLayout.counterMaxLength)
        
        btnRegister.setOnClickListener { callback.onRegisterClick(getInputFieldsValues()) }
    }
    
    private fun getInputFieldsValues(): InputFieldValues = with(binding) {
        return InputFieldValues(
            etName.text.toString(),
            etLogin.text.toString(),
            etPassword.text.toString(),
            etRepeatedPassword.text.toString()
        )
    }
    
    fun bindScreenState(registrationScreenState: RegistrationScreenState) = with(binding) {
        bindErrorList(nameInputLayout, registrationScreenState.validationResult.name)
        bindErrorList(loginInputLayout, registrationScreenState.validationResult.login)
        bindErrorList(passwordInputLayout, registrationScreenState.validationResult.password)
        bindErrorList(repeatedPasswordInputLayout, registrationScreenState.validationResult.repeatedPassword)
        
        btnRegister.setViewVisibility(registrationScreenState.buttonVisibility)
        progressBar.setViewVisibility(registrationScreenState.progressBarVisibility)
    }
    
    private fun bindErrorList(textInputLayout: TextInputLayout, errorList: PresentationErrorList?) {
        textInputLayout.error = errorList?.joinToString("\n") { context.getStringByReference(it.textReference) }
    }
    
    fun bindRegistrationStatus(registrationStatus: Boolean) {
        if (registrationStatus) {
            Toast.makeText(context, R.string.text_registration_user_added_successfully, Toast.LENGTH_SHORT).show()
        }
    }
    
}
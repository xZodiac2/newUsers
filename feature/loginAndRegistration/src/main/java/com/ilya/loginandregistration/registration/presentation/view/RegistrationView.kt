package com.ilya.loginandregistration.registration.presentation.view

import android.text.InputFilter.LengthFilter
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.ilya.core.getStringByReference
import com.ilya.loginandregistration.R
import com.ilya.loginandregistration.databinding.FragmentRegistrationBinding
import com.ilya.loginandregistration.registration.presentation.callback.RegistrationViewCallback
import com.ilya.loginandregistration.registration.presentation.error.PresentationErrorList
import com.ilya.loginandregistration.registration.presentation.models.InputFieldValues
import com.ilya.loginandregistration.registration.presentation.state.RegistrationViewState

class RegistrationView(
    private val binding: FragmentRegistrationBinding,
    private val callback: RegistrationViewCallback
) {
    
    init {
        initViews()
    }
    
    private fun initViews() = with(binding) {
        etName.filters += LengthFilter(nameInputLayout.counterMaxLength)
        etLogin.filters += LengthFilter(loginInputLayout.counterMaxLength)
        etPassword.filters += LengthFilter(passwordInputLayout.counterMaxLength)
        etRepeatedPassword.filters += LengthFilter(repeatedPasswordInputLayout.counterMaxLength)
        
        btnRegister.setOnClickListener { callback.onRegisterClick(getInputFieldsValues()) }
    }
    
    private fun getInputFieldsValues(): InputFieldValues = with(binding) {
        return InputFieldValues(etName.text.toString(), etLogin.text.toString(), etPassword.text.toString(), etRepeatedPassword.text.toString())
    }
    
    fun bind(registrationViewState: RegistrationViewState?) = with(binding) {
        registrationViewState ?: return
        
        bindErrorList(nameInputLayout, registrationViewState.validationResult.name)
        bindErrorList(loginInputLayout, registrationViewState.validationResult.login)
        bindErrorList(passwordInputLayout, registrationViewState.validationResult.password)
        bindErrorList(repeatedPasswordInputLayout, registrationViewState.validationResult.repeatedPassword)
        
        btnRegister.visibility = registrationViewState.buttonVisibility
        progressBar.visibility = registrationViewState.progressBarVisibility
        
        if (registrationViewState.isUserSuccessfullyRegistered) {
            Toast.makeText(etName.context, R.string.text_registration_user_added_successfully, Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun bindErrorList(textInputLayout: TextInputLayout, errorList: PresentationErrorList?) {
        textInputLayout.error = errorList?.joinToString("\n") { textInputLayout.context.getStringByReference(it.textReference) }
    }
    
}
package com.ilya.loginandregistration.registration.presentation

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.ilya.core.extract
import com.ilya.loginandregistration.R
import com.ilya.loginandregistration.registration.domain.usecases.inputValidators.CheckInputFieldsUseCase
import com.ilya.loginandregistration.registration.domain.models.InputFieldsValues
import com.ilya.loginandregistration.registration.domain.models.DataOfNewUser
import com.ilya.loginandregistration.registration.domain.usecases.RegisterNewUserUseCase
import com.ilya.loginandregistration.registration.presentation.models.InputFieldsLayouts
import com.ilya.loginandregistration.registration.presentation.navigation.RegistrationFragmentRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val registerNewUserUseCase: RegisterNewUserUseCase,
    private val checkInputFieldsUseCase: CheckInputFieldsUseCase,
    private val registrationFragmentRouter: RegistrationFragmentRouter
) : ViewModel() {
    
    fun registerNewUser(
        inputFieldsValues: InputFieldsValues,
        inputFieldsLayouts: InputFieldsLayouts,
        context: Context
    ) = with(inputFieldsValues) {
        if (inputFieldsIsCorrect(inputFieldsValues, inputFieldsLayouts, context)) {
            val name = this.name
            val login = this.login
            val password = this.password
            
            val user = DataOfNewUser(name, login, password)
            
            registerNewUserUseCase(user)
            Toast.makeText(context, context.getString(R.string.text_registration_user_added_successfully), Toast.LENGTH_SHORT).show()
            registrationFragmentRouter.backToLogin()
        }
    }
    
    private fun inputFieldsIsCorrect(
        inputFieldsValuesToCheck: InputFieldsValues,
        inputFieldsLayouts: InputFieldsLayouts,
        context: Context
    ): Boolean = with(inputFieldsLayouts) {
        checkInputFields(inputFieldsValuesToCheck, inputFieldsLayouts, context)
        return (
            nameLayout.error == null &&
            loginLayout.error == null &&
            passwordLayout.error == null &&
            repeatedPasswordLayout.error == null
        )
    }
    
    private fun checkInputFields(
        inputFieldsValuesToCheck: InputFieldsValues,
        inputFieldsLayouts: InputFieldsLayouts,
        context: Context
    ) = with(inputFieldsLayouts) {
        val errors = checkInputFieldsUseCase(inputFieldsValuesToCheck)
        
        nameLayout.error = errors.name?.extract(context)
        loginLayout.error = errors.login?.extract(context)
        passwordLayout.error = errors.password?.extract(context)
        repeatedPasswordLayout.error = errors.repeatedPassword?.extract(context)
    }
    
    
}
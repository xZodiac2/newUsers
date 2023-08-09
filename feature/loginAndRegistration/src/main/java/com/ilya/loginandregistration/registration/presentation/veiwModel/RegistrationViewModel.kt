package com.ilya.loginandregistration.registration.presentation.veiwModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ilya.core.computedMD5Hash
import com.ilya.loginandregistration.registration.domain.error.ErrorList
import com.ilya.loginandregistration.registration.domain.error.RegistrationDomainError
import com.ilya.loginandregistration.registration.domain.error.RegistrationValidationError
import com.ilya.loginandregistration.registration.domain.models.ErrorListWrapper
import com.ilya.loginandregistration.registration.domain.models.NecessaryLength
import com.ilya.loginandregistration.registration.domain.models.NewUserData
import com.ilya.loginandregistration.registration.domain.useCases.RegisterNewUserUseCase
import com.ilya.loginandregistration.registration.domain.useCases.ValidateInputFieldUseCase
import com.ilya.loginandregistration.registration.domain.validators.FieldsMatchValidator
import com.ilya.loginandregistration.registration.domain.validators.IllegalCharactersValidator
import com.ilya.loginandregistration.registration.domain.validators.IsEmptyValidator
import com.ilya.loginandregistration.registration.domain.validators.LengthValidator
import com.ilya.loginandregistration.registration.presentation.callback.RegistrationViewCallback
import com.ilya.loginandregistration.registration.presentation.error.PresentationErrorList
import com.ilya.loginandregistration.registration.presentation.error.RegistrationPresentationError
import com.ilya.loginandregistration.registration.presentation.models.InputFieldValues
import com.ilya.loginandregistration.registration.presentation.models.ValidationResult
import com.ilya.loginandregistration.registration.presentation.navigation.RegistrationFragmentRouter
import com.ilya.loginandregistration.registration.presentation.state.RegistrationViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val registerNewUserUseCase: RegisterNewUserUseCase,
) : ViewModel(), RegistrationViewCallback {
    
    private val _stateLiveData: MutableLiveData<RegistrationViewState> = MutableLiveData()
    val stateLiveData: LiveData<RegistrationViewState> = _stateLiveData
    
    lateinit var registrationFragmentRouter: RegistrationFragmentRouter
    
    override fun onRegisterClick(inputFieldValues: InputFieldValues) {
        val validationResult = getValidationResult(inputFieldValues)
        val inputFieldsIsCorrect = inputFieldsIsCorrect(validationResult)
        
        if (inputFieldsIsCorrect) {
            val newUserData = NewUserData(inputFieldValues.name, inputFieldValues.login, inputFieldValues.password.computedMD5Hash())
            
            var isUserRegisteredSuccessfully = false
            registerNewUserUseCase(newUserData)
                .onSuccess { isUserRegisteredSuccessfully = true }
                .onFailure {
                    when (it) {
                        is RegistrationDomainError.LoginAlreadyUsed -> {
                            val state = getOrCreateState()
                            
                            _stateLiveData.value = state.copy(
                                validationResult = state.validationResult.copy(
                                    login = listOf(RegistrationPresentationError.LoginAlreadyUsed)
                                )
                            )
                        }
                    }
                }
            
            _stateLiveData.value = getOrCreateState().copy(isUserSuccessfullyRegistered = isUserRegisteredSuccessfully)
            
            if (isUserRegisteredSuccessfully) registrationFragmentRouter.backToLogin()
    
        } else {
            _stateLiveData.value = getOrCreateState().copy(validationResult = validationResult)
        }
    }
    
    private fun getOrCreateState(): RegistrationViewState {
        return _stateLiveData.value ?: RegistrationViewState(ValidationResult(null, null, null, null), false)
    }
    
    private fun inputFieldsIsCorrect(validationResult: ValidationResult): Boolean {
        return (
            validationResult.name == null &&
            validationResult.login == null &&
            validationResult.password == null &&
            validationResult.repeatedPassword == null
        )
    }
    
    private fun getValidationResult(inputFieldsValues: InputFieldValues): ValidationResult = with(inputFieldsValues) {
        val nameValidation = ValidateInputFieldUseCase(IsEmptyValidator())
        val loginValidation = ValidateInputFieldUseCase(
            IsEmptyValidator(),
            LengthValidator(NecessaryLength.Login()),
            IllegalCharactersValidator("""\W""".toRegex())
        )
        val passwordValidation = ValidateInputFieldUseCase(LengthValidator(NecessaryLength.Password()))
        val repeatedPasswordValidation = ValidateInputFieldUseCase(
            LengthValidator(NecessaryLength.Password()),
            FieldsMatchValidator(password)
        )
        
        return ValidationResult(
            name = nameValidation(name).fold(
                onSuccess = { null },
                onFailure = { mapErrorList((it as ErrorListWrapper).errorList) }
            ),
            login = loginValidation(login).fold(
                onSuccess = { null },
                onFailure = { mapErrorList((it as ErrorListWrapper).errorList) }
            ),
            password = passwordValidation(password).fold(
                onSuccess = { null },
                onFailure = { mapErrorList((it as ErrorListWrapper).errorList) }
            ),
            repeatedPassword = repeatedPasswordValidation(repeatedPassword).fold(
                onSuccess = { null },
                onFailure = { mapErrorList((it as ErrorListWrapper).errorList) }
            )
        )
    }
    
    private fun mapErrorList(errorList: ErrorList): PresentationErrorList {
        return errorList.mapNotNull { mapError(it) }
    }
    
    private fun mapError(error: RegistrationValidationError?): RegistrationPresentationError? {
        return when (error) {
            is RegistrationValidationError.LengthError.Login -> RegistrationPresentationError.LengthError.Login
            is RegistrationValidationError.LengthError.Password -> RegistrationPresentationError.LengthError.Password
            is RegistrationValidationError.LoginAlreadyUsed -> RegistrationPresentationError.LoginAlreadyUsed
            is RegistrationValidationError.IllegalCharacter -> RegistrationPresentationError.IllegalCharacter
            is RegistrationValidationError.FieldsDoNotMatch -> RegistrationPresentationError.FieldsDoNotMatch
            is RegistrationValidationError.FieldIsEmpty -> RegistrationPresentationError.FieldIsEmpty
            null -> null
        }
    }
    
}
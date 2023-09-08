package com.ilya.loginandregistration.registration.presentation.veiwModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilya.core.computedMD5Hash
import com.ilya.core.enums.ViewVisibility
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val registerNewUserUseCase: RegisterNewUserUseCase,
) : ViewModel(), RegistrationViewCallback {
    
    private val _stateLiveData: MutableLiveData<RegistrationViewState> = MutableLiveData()
    val stateLiveData: LiveData<RegistrationViewState> = _stateLiveData
    
    lateinit var registrationFragmentRouter: RegistrationFragmentRouter
    
    override fun onRegisterClick(inputFieldValues: InputFieldValues) = viewModelScope.launch {
        val validationResult = getValidationResult(inputFieldValues)
        val inputFieldsIsCorrect = inputFieldsIsCorrect(validationResult)
        
        if (inputFieldsIsCorrect) {
            val newUserData = NewUserData(inputFieldValues.name, inputFieldValues.login, inputFieldValues.password.computedMD5Hash())
            
            _stateLiveData.value = getOrCreateState().copy(buttonVisibility = ViewVisibility.GONE, progressBarVisibility = ViewVisibility.VISIBLE)
            
            registerUser(newUserData)
                .onSuccess {
                    _stateLiveData.value = getOrCreateState().copy(isUserSuccessfullyRegistered = true)
                    registrationFragmentRouter.backToLogin()
                }
                .onFailure { error ->
                    error as RegistrationDomainError
                    
                    when (error) {
                        is RegistrationDomainError.LoginAlreadyUsed -> {
                            _stateLiveData.value = getOrCreateState().copy(
                                isUserSuccessfullyRegistered = false,
                                validationResult = validationResult.copy(
                                    login = listOf(RegistrationPresentationError.LoginAlreadyUsed)
                                )
                            )
                        }
                        
                        is RegistrationDomainError.IllegalRegistrationArgument -> {
                            Log.d("msg", "Expected argument with type NewUserData")
                            _stateLiveData.value = getOrCreateState().copy(isUserSuccessfullyRegistered = false)
                        }
                        
                        is RegistrationDomainError.UnknownError -> {
                            _stateLiveData.value = getOrCreateState().copy(
                                registrationError = RegistrationPresentationError.UnknownError,
                                isUserSuccessfullyRegistered = false
                            )
                        }
                    }
                }
            
            _stateLiveData.value = getOrCreateState().copy(buttonVisibility = ViewVisibility.VISIBLE, progressBarVisibility = ViewVisibility.GONE)
            
        } else {
            _stateLiveData.value = getOrCreateState().copy(validationResult = validationResult)
        }
    }
    
    private suspend fun registerUser(newUserData: NewUserData): Result<Unit> = withContext(Dispatchers.IO) {
        return@withContext registerNewUserUseCase.execute(newUserData)
    }
    
    private fun getOrCreateState(): RegistrationViewState {
        return _stateLiveData.value ?: RegistrationViewState(
            validationResult = ValidationResult(null, null, null, null),
            registrationError = null,
            isUserSuccessfullyRegistered = false,
            buttonVisibility = ViewVisibility.VISIBLE,
            progressBarVisibility = ViewVisibility.GONE
        )
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
            LengthValidator(NecessaryLength.Login(NECESSARY_LOGIN_LENGTH)),
            IllegalCharactersValidator("""\W""".toRegex())
        )
        val passwordValidation =
            ValidateInputFieldUseCase(LengthValidator(NecessaryLength.Password(NECESSARY_PASSWORD_LENGTH)))
        val repeatedPasswordValidation = ValidateInputFieldUseCase(
            LengthValidator(NecessaryLength.Password(NECESSARY_PASSWORD_LENGTH)),
            FieldsMatchValidator(password)
        )
        
        return ValidationResult(
            name = nameValidation(name).defineValidationResult(),
            login = loginValidation(login).defineValidationResult(),
            password = passwordValidation(password).defineValidationResult(),
            repeatedPassword = repeatedPasswordValidation(repeatedPassword).defineValidationResult()
        )
    }
    
    private fun Result<Unit>.defineValidationResult(): PresentationErrorList? {
        return this.fold(
            onSuccess = { null },
            onFailure = { mapErrorList((it as ErrorListWrapper).errorList) }
        )
    }
    
    private fun mapErrorList(errorList: ErrorList): PresentationErrorList {
        return errorList.mapNotNull { mapError(it) }
    }
    
    private fun mapError(error: RegistrationValidationError?): RegistrationPresentationError? {
        return when (error) {
            is RegistrationValidationError.LengthError.Login -> RegistrationPresentationError.LengthError.Login
            is RegistrationValidationError.LengthError.Password -> RegistrationPresentationError.LengthError.Password
            is RegistrationValidationError.IllegalCharacter -> RegistrationPresentationError.IllegalCharacter
            is RegistrationValidationError.FieldsDoNotMatch -> RegistrationPresentationError.FieldsDoNotMatch
            is RegistrationValidationError.FieldIsEmpty -> RegistrationPresentationError.FieldIsEmpty
            null -> null
        }
    }
    
    companion object {
        const val NECESSARY_LOGIN_LENGTH = 3
        const val NECESSARY_PASSWORD_LENGTH = 8
    }
    
}
package com.ilya.loginandregistration.registration.presentation.veiwModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilya.core.computedMD5Hash
import com.ilya.core.enums.LoadingState
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
import com.ilya.loginandregistration.registration.presentation.state.RegistrationScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val registerNewUserUseCase: RegisterNewUserUseCase,
) : ViewModel(), RegistrationViewCallback {
    
    private val _screenStateFlow = MutableStateFlow(RegistrationScreenState())
    val screenStateFlow = _screenStateFlow.asStateFlow()
    
    private val _userRegistrationStatusSharedFlow = MutableSharedFlow<Boolean>()
    val userRegistrationStatusSharedFlow = _userRegistrationStatusSharedFlow.asSharedFlow()
    
    lateinit var registrationFragmentRouter: RegistrationFragmentRouter
    
    override fun onRegisterClick(inputFieldValues: InputFieldValues) {
        viewModelScope.launch {
            val validationResult = getValidationResult(inputFieldValues)
            
            if (inputFieldsIsCorrect(validationResult)) {
                val newUserData = NewUserData(
                    inputFieldValues.name,
                    inputFieldValues.login,
                    inputFieldValues.password.computedMD5Hash()
                )
                
                toggleViewVisibilityByLoadingState(LoadingState.LOADING)
                
                registerUser(newUserData)
                    .onSuccess {
                        _userRegistrationStatusSharedFlow.emit(SUCCESSFULLY)
                        registrationFragmentRouter.backToLogin()
                        toggleViewVisibilityByLoadingState(LoadingState.DONE)
                    }
                    .onFailure { (it as RegistrationDomainError).react(validationResult) }
                
                
            } else {
                _screenStateFlow.value = _screenStateFlow.value.copy(validationResult = validationResult)
            }
        }
    }
    
    private suspend fun RegistrationDomainError.react(validationResult: ValidationResult) {
        when (this) {
            is RegistrationDomainError.LoginAlreadyUsed -> {
                _screenStateFlow.value = _screenStateFlow.value.copy(
                    validationResult = validationResult.copy(
                        login = listOf(RegistrationPresentationError.LoginAlreadyUsed)
                    )
                )
                _userRegistrationStatusSharedFlow.emit(UNSUCCESSFUL)
            }
            
            is RegistrationDomainError.IllegalRegistrationArgument -> {
                Log.d("msg", "Expected argument with type NewUserData")
                toggleViewVisibilityByLoadingState(LoadingState.ERROR)
                _screenStateFlow.value = _screenStateFlow.value.copy(
                    registrationError = RegistrationPresentationError.SomethingWentWrong
                )
                _userRegistrationStatusSharedFlow.emit(UNSUCCESSFUL)
            }
            
            is RegistrationDomainError.UnknownError -> {
                toggleViewVisibilityByLoadingState(LoadingState.ERROR)
                _screenStateFlow.value = _screenStateFlow.value.copy(
                    registrationError = RegistrationPresentationError.UnknownError
                )
                _userRegistrationStatusSharedFlow.emit(UNSUCCESSFUL)
            }
        }
    }
    
    private fun toggleViewVisibilityByLoadingState(loadingState: LoadingState) {
        when (loadingState) {
            LoadingState.LOADING -> {
                _screenStateFlow.value = _screenStateFlow.value.copy(
                    buttonVisibility = ViewVisibility.GONE,
                    progressBarVisibility = ViewVisibility.VISIBLE,
                    errorVisibility = ViewVisibility.GONE
                )
            }
            
            LoadingState.DONE -> {
                _screenStateFlow.value = _screenStateFlow.value.copy(
                    buttonVisibility = ViewVisibility.VISIBLE,
                    progressBarVisibility = ViewVisibility.GONE,
                    errorVisibility = ViewVisibility.GONE
                )
            }
            
            LoadingState.ERROR -> {
                _screenStateFlow.value = _screenStateFlow.value.copy(
                    errorVisibility = ViewVisibility.VISIBLE,
                    buttonVisibility = ViewVisibility.VISIBLE,
                    progressBarVisibility = ViewVisibility.GONE
                )
            }
        }
    }
    
    private suspend fun registerUser(newUserData: NewUserData): Result<Unit> = withContext(Dispatchers.IO) {
        return@withContext registerNewUserUseCase.execute(newUserData)
    }
    
    private fun inputFieldsIsCorrect(validationResult: ValidationResult): Boolean {
        return validationResult.name == null && validationResult.login == null && validationResult.password == null && validationResult.repeatedPassword == null
    }
    
    private suspend fun getValidationResult(inputFieldsValues: InputFieldValues): ValidationResult =
        with(inputFieldsValues) {
            val nameValidation = ValidateInputFieldUseCase(IsEmptyValidator())
            val loginValidation = ValidateInputFieldUseCase(
                IsEmptyValidator(),
                LengthValidator(NecessaryLength.Login(NECESSARY_LOGIN_LENGTH)),
                IllegalCharactersValidator("""\W""".toRegex())
            )
            val passwordValidation =
                ValidateInputFieldUseCase(LengthValidator(NecessaryLength.Password(NECESSARY_PASSWORD_LENGTH)))
            val repeatedPasswordValidation = ValidateInputFieldUseCase(
                LengthValidator(NecessaryLength.Password(NECESSARY_PASSWORD_LENGTH)), FieldsMatchValidator(password)
            )
            
            return ValidationResult(
                name = nameValidation.execute(name).defineValidationResult(),
                login = loginValidation.execute(login).defineValidationResult(),
                password = passwordValidation.execute(password).defineValidationResult(),
                repeatedPassword = repeatedPasswordValidation.execute(repeatedPassword).defineValidationResult()
            )
        }
    
    private fun Result<Unit>.defineValidationResult(): PresentationErrorList? {
        return this.fold(
            onSuccess = { null },
            onFailure = { (it as ErrorListWrapper).errorList.mapToPresentationErrorList() }
        )
    }
    
    private fun ErrorList.mapToPresentationErrorList(): PresentationErrorList {
        return this.mapNotNull { it?.mapToPresentationError() }
    }
    
    private fun RegistrationValidationError.mapToPresentationError(): RegistrationPresentationError {
        return when (this) {
            is RegistrationValidationError.LengthError.Login -> RegistrationPresentationError.LengthError.Login
            is RegistrationValidationError.LengthError.Password -> RegistrationPresentationError.LengthError.Password
            is RegistrationValidationError.IllegalCharacter -> RegistrationPresentationError.IllegalCharacter
            is RegistrationValidationError.FieldsDoNotMatch -> RegistrationPresentationError.FieldsDoNotMatch
            is RegistrationValidationError.FieldIsEmpty -> RegistrationPresentationError.FieldIsEmpty
        }
    }
    
    companion object {
        const val NECESSARY_LOGIN_LENGTH = 3
        const val NECESSARY_PASSWORD_LENGTH = 8
        private const val UNSUCCESSFUL = false
        private const val SUCCESSFULLY = true
    }
    
}
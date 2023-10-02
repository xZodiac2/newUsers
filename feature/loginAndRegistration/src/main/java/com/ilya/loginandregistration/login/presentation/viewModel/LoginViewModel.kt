package com.ilya.loginandregistration.login.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilya.core.enums.ViewVisibility
import com.ilya.loginandregistration.login.domain.error.LoginDomainError
import com.ilya.loginandregistration.login.domain.models.LoggedInUserData
import com.ilya.loginandregistration.login.domain.models.UserLoginParams
import com.ilya.loginandregistration.login.domain.useCases.FindUserUseCase
import com.ilya.loginandregistration.login.presentation.callback.LoginViewCallback
import com.ilya.loginandregistration.login.presentation.error.LoginPresentationError
import com.ilya.loginandregistration.login.presentation.navigation.LoginFragmentRouter
import com.ilya.loginandregistration.login.presentation.state.LoginScreenState
import com.ilya.loginandregistration.login.presentation.state.LoginWaitingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val findUserUseCase: FindUserUseCase,
) : ViewModel(), LoginViewCallback {
    
    private val _screenStateFlow = MutableStateFlow(LoginScreenState())
    val screenStateFlow = _screenStateFlow.asStateFlow()
    
    lateinit var loginFragmentRouter: LoginFragmentRouter
    
    override fun onLoginClick(loginParams: UserLoginParams) {
        viewModelScope.launch {
            toggleViewVisibilityByWaitingState(LoginWaitingState.WaitingForResponse)
            
            findUser(loginParams)
                .onSuccess { loginFragmentRouter.goToGreeting(it.login) }
                .onFailure { (it as LoginDomainError).react() }
            
            toggleViewVisibilityByWaitingState(LoginWaitingState.WaitingForUser)
        }
    }
    
    private fun LoginDomainError.react() {
        changeErrorInState(this.mapToPresentationError())
    }
    
    private fun toggleViewVisibilityByWaitingState(loginWaitingState: LoginWaitingState) {
        when (loginWaitingState) {
            is LoginWaitingState.WaitingForResponse -> {
                _screenStateFlow.value = _screenStateFlow.value.copy(
                    buttonVisibility = ViewVisibility.GONE,
                    progressBarVisibility = ViewVisibility.VISIBLE
                )
            }
            
            is LoginWaitingState.WaitingForUser -> {
                _screenStateFlow.value = _screenStateFlow.value.copy(
                    buttonVisibility = ViewVisibility.VISIBLE,
                    progressBarVisibility = ViewVisibility.GONE
                )
            }
        }
    }
    
    private fun LoginDomainError.mapToPresentationError(): LoginPresentationError {
        return when (this) {
            is LoginDomainError.UnknownError -> LoginPresentationError.UnknownError
            is LoginDomainError.WrongLoginOrPassword -> LoginPresentationError.WrongLoginOrPassword
            is LoginDomainError.WrongLoginArgument -> LoginPresentationError.SomethingWentWrong
        }
    }
    
    private fun changeErrorInState(error: LoginPresentationError) {
        _screenStateFlow.value = _screenStateFlow.value.copy(loginError = error)
    }
    
    private suspend fun findUser(loginParams: UserLoginParams): Result<LoggedInUserData> = withContext(Dispatchers.IO) {
        return@withContext findUserUseCase.execute(loginParams)
    }
    
    override fun onOfferToRegisterClick() {
        loginFragmentRouter.goToRegistration()
    }
    
    override fun onInputFieldsChanged() {
        _screenStateFlow.value = _screenStateFlow.value.copy(loginError = null)
    }
    
}
package com.ilya.loginandregistration.login.presentation.viewModel

import android.util.Log
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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val findUserUseCase: FindUserUseCase,
) : ViewModel(), LoginViewCallback {
    
    private val _screenStateFlow: MutableStateFlow<LoginScreenState> = MutableStateFlow(LoginScreenState())
    val screenStateFlow: StateFlow<LoginScreenState> = _screenStateFlow
    
    lateinit var loginFragmentRouter: LoginFragmentRouter
    
    override fun onLoginClick(loginParams: UserLoginParams) {
        viewModelScope.launch {
            _screenStateFlow.value = _screenStateFlow.value.copy(
                buttonVisibility = ViewVisibility.GONE,
                progressBarVisibility = ViewVisibility.VISIBLE
            )
            
            findUser(loginParams)
                .onSuccess { loginFragmentRouter.goToGreeting(it.login) }
                .onFailure { error ->
                    error as LoginDomainError
                    
                    when (error) {
                        is LoginDomainError.WrongLoginOrPassword -> {
                            _screenStateFlow.value =
                                _screenStateFlow.value.copy(loginError = LoginPresentationError.WrongLoginOrPasswordError)
                        }
                        
                        is LoginDomainError.UnknownError -> {
                            _screenStateFlow.value =
                                _screenStateFlow.value.copy(loginError = LoginPresentationError.UnknownError)
                        }
                        
                        is LoginDomainError.WrongLoginArgument -> {
                            _screenStateFlow.value =
                                _screenStateFlow.value.copy(loginError = LoginPresentationError.SomethingWentWrong)
                            Log.e("msg", "Expected argument with type UserLoginParams")
                        }
                    }
                }
            
            _screenStateFlow.value = _screenStateFlow.value.copy(
                buttonVisibility = ViewVisibility.VISIBLE,
                progressBarVisibility = ViewVisibility.GONE
            )
        }
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
package com.ilya.loginandregistration.login.presentation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ilya.loginandregistration.login.domain.error.LoginDomainError
import com.ilya.loginandregistration.login.domain.models.UserLoginParams
import com.ilya.loginandregistration.login.domain.useCases.FindUserUseCase
import com.ilya.loginandregistration.login.presentation.callback.LoginViewCallback
import com.ilya.loginandregistration.login.presentation.error.LoginPresentationError
import com.ilya.loginandregistration.login.presentation.navigation.LoginFragmentRouter
import com.ilya.loginandregistration.login.presentation.state.LoginViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val findUserUseCase: FindUserUseCase,
) : ViewModel(), LoginViewCallback {
    
    private val _stateLiveData: MutableLiveData<LoginViewState> = MutableLiveData()
    val stateLiveData: LiveData<LoginViewState> = _stateLiveData
    
    lateinit var loginFragmentRouter: LoginFragmentRouter
    
    override fun onLoginClick(loginParams: UserLoginParams) {
        findUserUseCase(loginParams)
            .onSuccess { loginFragmentRouter.goToGreeting(it.login) }
            .onFailure { error ->
                when (error) {
                    is LoginDomainError.WrongLoginOrPassword ->
                        _stateLiveData.value = getOrCreateState().copy(loginError = LoginPresentationError.WrongLoginOrPasswordError)
                    
                    is LoginDomainError.UnknownError ->
                        _stateLiveData.value = getOrCreateState().copy(loginError = LoginPresentationError.UnknownError)
                    
                    is LoginDomainError.WrongLoginArgument -> Log.e("msg", "Expected argument with type UserLoginParams")
                }
            }
    }
    
    private fun getOrCreateState(): LoginViewState {
        return _stateLiveData.value ?: LoginViewState(null)
    }
    
    override fun onOfferToRegisterClick() {
        loginFragmentRouter.goToRegistration()
    }
    
    
}
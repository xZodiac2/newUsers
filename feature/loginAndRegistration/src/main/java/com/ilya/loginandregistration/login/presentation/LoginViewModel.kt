package com.ilya.loginandregistration.login.presentation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ilya.core.extract
import com.ilya.loginandregistration.login.domain.usecases.FindUserByLoginParamsUseCase
import com.ilya.loginandregistration.login.domain.models.LoginParams
import com.ilya.loginandregistration.login.domain.exception.UserIsNotFoundByLoginParamsException
import com.ilya.loginandregistration.login.presentation.error.LoginError
import com.ilya.loginandregistration.login.presentation.navigation.LoginFragmentRouter
import com.ilya.loginandregistration.registration.domain.usecases.RegisterNewUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userFindUserByLoginParamsUseCase: FindUserByLoginParamsUseCase,
    private val loginFragmentRouter: LoginFragmentRouter,
): ViewModel() {
    
    private val _wrongLoginOrPasswordErrorMessage = MutableLiveData("")
    var wrongLoginOrPasswordErrorMessage: LiveData<String> = _wrongLoginOrPasswordErrorMessage
    
    fun login(loginParams: LoginParams, context: Context) {
        try {
            val user = userFindUserByLoginParamsUseCase(loginParams)
            loginFragmentRouter.goToGreeting(user.id)
        } catch (ex: UserIsNotFoundByLoginParamsException) {
            _wrongLoginOrPasswordErrorMessage.value = LoginError.WrongLoginOrPasswordError.extract(context)
        }
    }
    
    fun goToRegistration() {
        loginFragmentRouter.goToRegistration()
    }
    
}
package com.ilya.greeting.presentation.viewModel

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilya.core.TextReference
import com.ilya.core.enums.ViewVisibility
import com.ilya.greeting.R
import com.ilya.greeting.domain.models.GreetingUserData
import com.ilya.greeting.domain.useCases.FindUserUseCase
import com.ilya.greeting.presentation.callback.GreetingViewCallback
import com.ilya.greeting.presentation.navigation.GreetingFragmentRouter
import com.ilya.greeting.presentation.state.GreetingViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class GreetingViewModel @Inject constructor(
    private val findUserUseCase: FindUserUseCase,
) : ViewModel(), GreetingViewCallback {
    
    private val _stateLiveData: MutableStateFlow<GreetingViewState> = MutableStateFlow(GreetingViewState())
    val stateLiveData: StateFlow<GreetingViewState> = _stateLiveData
    
    lateinit var greetingFragmentRouter: GreetingFragmentRouter
    
    override fun onLogoutClick() {
        backToLogin()
    }
    
    fun getUser(args: Bundle) = viewModelScope.launch {
        val state = _stateLiveData.value
        val userLogin = args.getString(KEY_USER_LOGIN)
        
        if (userLogin == null) {
            backToLogin()
            return@launch
        }
        
        if (state.user == null) {
            _stateLiveData.value = state.copy(
                userNameVisibility = ViewVisibility.GONE,
                progressBarVisibility = ViewVisibility.VISIBLE
            )
            
            findUser(userLogin)
                .onSuccess {
                    _stateLiveData.value = state.copy(
                        user = it,
                        greetingTextReference = TextReference.Resource(R.string.text_greeting, listOf(it.name))
                    )
                    
                }
                .onFailure { backToLogin() }
        } else {
            _stateLiveData.value = state.copy(
                greetingTextReference = TextReference.Resource(
                    R.string.text_greeting,
                    listOf(state.user.name)
                )
            )
        }
        
        
        _stateLiveData.value = _stateLiveData.value.copy(
            userNameVisibility = ViewVisibility.VISIBLE,
            progressBarVisibility = ViewVisibility.GONE
        )
    }
    
    private suspend fun findUser(userLogin: String): Result<GreetingUserData> = withContext(Dispatchers.IO) {
        return@withContext findUserUseCase.execute(userLogin)
    }
    
    private fun backToLogin() {
        greetingFragmentRouter.backToLogin()
    }
    
    private companion object {
        const val KEY_USER_LOGIN = "userLogin"
    }
    
}
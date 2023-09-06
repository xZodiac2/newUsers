package com.ilya.greeting.presentation.viewModel

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilya.core.TextReference
import com.ilya.greeting.R
import com.ilya.greeting.domain.models.GreetingUserData
import com.ilya.greeting.domain.useCases.FindUserUseCase
import com.ilya.greeting.presentation.callback.GreetingViewCallback
import com.ilya.greeting.presentation.navigation.GreetingFragmentRouter
import com.ilya.greeting.presentation.state.GreetingViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class GreetingViewModel @Inject constructor(
    private val findUserUseCase: FindUserUseCase,
) : ViewModel(), GreetingViewCallback {
    
    private val _stateLiveData: MutableLiveData<GreetingViewState> = MutableLiveData()
    val stateLiveData: LiveData<GreetingViewState> = _stateLiveData
    
    lateinit var greetingFragmentRouter: GreetingFragmentRouter
    
    override fun onLogoutClick() {
        backToLogin()
    }
    
    fun getUser(args: Bundle) = viewModelScope.launch {
        val userLogin = args.getString(KEY_USER_LOGIN)
        
        if (userLogin == null) {
            backToLogin()
            return@launch
        }
        
        if (getOrCreateState().user == null) {
            _stateLiveData.value = getOrCreateState().copy(contentVisibility = View.GONE, progressBarVisibility = View.VISIBLE)
            
            findUser(userLogin)
                .onSuccess {
                    _stateLiveData.value = getOrCreateState().copy(
                        user = it,
                        greetingTextReference = TextReference.Resource(R.string.text_greeting, listOf(it.name))
                    )
                    
                }
                .onFailure { backToLogin() }
        } else {
            val state = getOrCreateState()
            _stateLiveData.value = state.copy(
                greetingTextReference = TextReference.Resource(R.string.text_greeting, listOf(state.user?.name.toString()))
            )
        }
        
        
        _stateLiveData.value = getOrCreateState().copy(contentVisibility = View.VISIBLE, progressBarVisibility = View.GONE)
    }
    
    private suspend fun findUser(userLogin: String): Result<GreetingUserData> = withContext(Dispatchers.IO) {
        return@withContext findUserUseCase.execute(userLogin)
    }
    
    private fun getOrCreateState(): GreetingViewState {
        return stateLiveData.value ?: GreetingViewState(
            greetingTextReference = TextReference.Resource(R.string.text_greeting, listOf("")),
            contentVisibility = View.GONE,
            progressBarVisibility = View.VISIBLE
        )
    }
    
    private fun backToLogin() {
        greetingFragmentRouter.backToLogin()
    }
    
    private companion object {
        const val KEY_USER_LOGIN = "userLogin"
    }
    
}
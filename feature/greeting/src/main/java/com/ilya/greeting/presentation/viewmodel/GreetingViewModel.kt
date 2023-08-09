package com.ilya.greeting.presentation.viewModel

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ilya.core.TextReference
import com.ilya.greeting.R
import com.ilya.greeting.domain.useCases.FindUserUseCase
import com.ilya.greeting.presentation.callback.GreetingViewCallback
import com.ilya.greeting.presentation.navigation.GreetingFragmentRouter
import com.ilya.greeting.presentation.state.GreetingViewState
import dagger.hilt.android.lifecycle.HiltViewModel
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
    
    fun findUser(args: Bundle) {
        val userLogin = args.getString(KEY_USER_LOGIN)
        
        if (userLogin == null) {
            backToLogin()
            return
        }
        
        if (getOrCreateState().user == null) {
            findUserUseCase(userLogin)
                .onSuccess {
                    _stateLiveData.value = getOrCreateState().copy(user = it)
                    _stateLiveData.value =
                        getOrCreateState().copy(
                            greetingTextReference = TextReference.Resource(R.string.text_greeting, listOf(it.name))
                        )
                }
                .onFailure { backToLogin() }
        } else {
            val state = getOrCreateState()
            _stateLiveData.value = state.copy(greetingTextReference = TextReference.Resource(R.string.text_greeting, listOf(state.user?.name.toString())))
        }
    }
    
    
    private fun getOrCreateState(): GreetingViewState {
        return stateLiveData.value ?: GreetingViewState(TextReference.Resource(R.string.text_greeting))
    }
    
    private fun backToLogin() {
        greetingFragmentRouter.backToLogin()
    }
    
    private companion object {
        const val KEY_USER_LOGIN = "userLogin"
    }
    
}
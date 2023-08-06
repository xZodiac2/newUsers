package com.ilya.greeting.presentation

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ilya.greeting.R
import com.ilya.greeting.domain.usecases.FindUserByIdUseCase
import com.ilya.greeting.domain.exception.UserIsNotFoundByIdException
import com.ilya.greeting.domain.models.User
import com.ilya.greeting.presentation.navigation.GreetingFragmentRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GreetingViewModel @Inject constructor(
    private val findUserByIdUseCase: FindUserByIdUseCase,
    private val greetingFragmentRouter: GreetingFragmentRouter
): ViewModel() {
    
    private val _userGreeting = MutableLiveData("sdfg")
    var userGreeting: LiveData<String> = _userGreeting
    
    fun findUserAndMapHisName(greetingFragmentArgs: Bundle, context: Context) {
        val userId = greetingFragmentArgs.getInt(KEY_USER_ID)
        if (!userIdIsNotDefault(userId)) backToLogin()
        
        try {
            val user: User = findUserByIdUseCase(userId)
            _userGreeting.value = "${context.getString(R.string.text_greeting)} ${user.name}"
        } catch (ex: UserIsNotFoundByIdException) {
            backToLogin()
        }
    }
    
    fun backToLogin() {
        greetingFragmentRouter.backToLogin()
    }
    
    private fun userIdIsNotDefault(userId: Int): Boolean {
        return userId != DEFAULT_USER_ID
    }
    
    companion object {
        private const val DEFAULT_USER_ID = -1
        private const val KEY_USER_ID = "userId"
    }
    
}

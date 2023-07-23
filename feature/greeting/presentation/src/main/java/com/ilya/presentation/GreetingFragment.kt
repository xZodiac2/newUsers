package com.ilya.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ilya.data.users.UsersRepository
import com.ilya.core.exceptions.UserNotFoundException
import com.ilya.domain.usecases.FindUserByIdUseCase
import com.ilya.presentation.databinding.FragmentGreetingBinding
import com.ilya.presentation.navigation.GreetingFragmentRouter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GreetingFragment : Fragment() {
    
    @Inject lateinit var greetingFragmentRouter: GreetingFragmentRouter
    @Inject lateinit var usersRepository: UsersRepository
    
    private lateinit var binding: FragmentGreetingBinding
    private lateinit var findUserByIdUseCase: FindUserByIdUseCase
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentGreetingBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.btnLogout.setOnClickListener { greetingFragmentRouter.backToLogin() }
        
        val userId = requireArguments().getInt(KEY_USER_ID, DEFAULT_USER_ID)
        if (userId == DEFAULT_USER_ID) {
            greetingFragmentRouter.backToLogin()
        }
        
        findUserByIdUseCase = FindUserByIdUseCase(usersRepository, userId)
        
        try {
            val signedUser = findUserByIdUseCase.execute()
            binding.tvGreeting.text = "${getString(R.string.text_greeting)} ${signedUser.name}"
        } catch (userNotFoundEx: UserNotFoundException) {
            greetingFragmentRouter.backToLogin()
        }
        
    }
    
    companion object {
        private const val KEY_USER_ID = "userId"
        private const val DEFAULT_USER_ID = -1
    }
    
}
package com.ilya.greeting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ilya.data.UsersRepository
import com.ilya.greeting.databinding.FragmentGreetingBinding
import com.ilya.greeting.navigation.GreetingFragmentRouter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class GreetingFragment : Fragment() {
    
    @Inject lateinit var usersRepository: UsersRepository
    @Inject lateinit var greetingFragmentRouter: GreetingFragmentRouter
    
    private lateinit var binding: FragmentGreetingBinding
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentGreetingBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val userId = requireArguments().getInt(KEY_USER_ID, DEFAULT_USER_ID)
        if (userId == DEFAULT_USER_ID) {
            greetingFragmentRouter.backToLogin()
        }
        
        val signedUser = usersRepository.getUserById(userId)
        if (signedUser == null) {
            greetingFragmentRouter.backToLogin()
        }
        
        binding.apply {
            tvGreeting.text = "${getString(R.string.text_greeting)} ${signedUser?.name}"
            btnLogout.setOnClickListener { greetingFragmentRouter.backToLogin() }
        }
        
    }
    
    companion object {
        
        private const val KEY_USER_ID = "userId"
        private const val DEFAULT_USER_ID = -1
    }
    
}

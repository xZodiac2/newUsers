package com.ilya.usersupgrade

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavArgs
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.ilya.usersupgrade.databinding.FragmentUserGreetingBinding

class UserGreetingFragment : Fragment() {
    
    private lateinit var binding: FragmentUserGreetingBinding
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentUserGreetingBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val userId = arguments?.getInt(KEY_USER_ID, UNDEFINED_USER_ID)
        if (userId == UNDEFINED_USER_ID) {
            backToLogin()
        }
        
        val signedUser = getUsersRepository().findUserById(userId)
        if (signedUser == null) {
            backToLogin()
        }
        
        binding.btnLogout.setOnClickListener { backToLogin() }
        binding.tvGreeting.text = getString(R.string.text_greeting) + signedUser?.name
    }
    
    private fun backToLogin() {
        getNavController().navigate(UserGreetingFragmentDirections.actionUserGreetingFragmentToLoginFragment())
    }
    
    companion object {
        private const val KEY_USER_ID = "userId"
        private const val UNDEFINED_USER_ID = -1
    }
    
}
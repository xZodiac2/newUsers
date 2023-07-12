package com.ilya.usersupgrade

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavArgs
import androidx.navigation.NavController
import androidx.navigation.fragment.navArgs
import com.ilya.usersupgrade.databinding.FragmentUserGreetingBinding

class UserGreetingFragment : Fragment() {
    
    private lateinit var binding: FragmentUserGreetingBinding
    private var userId: Int? = null
    private var signedUser: User? = null
    
    override fun onAttach(context: Context) {
        super.onAttach(context)
        
        userId = arguments?.getInt(KEY_USER_ID)
        signedUser = (requireActivity().applicationContext as UsersApplication).usersRepository.findUserById(userId)
        if (signedUser == null) {
            backToLogin()
        }
    }
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentUserGreetingBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.btnUnlogin.setOnClickListener { backToLogin() }
        binding.tvGreeting.text = getString(R.string.text_greeting) + signedUser?.name
    }
    
    private fun backToLogin() {
        (requireActivity().applicationContext as UsersApplication)
            .navController
            .navigate(UserGreetingFragmentDirections.actionUserGreetingFragmentToLoginFragment())
    }
    
    companion object {
        private const val KEY_USER_ID = "userId"
    }
    
}
package com.ilya.usersupgrade

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.accessibility.AccessibilityViewCommand.MoveWindowArguments
import androidx.navigation.NavController
import com.ilya.usersupgrade.databinding.FragmentLoginBinding
import com.ilya.usersupgrade.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment() {
    
    private lateinit var binding: FragmentRegistrationBinding
    private lateinit var usersRepository: UsersRepository
    private lateinit var navController: NavController
    
    private val passwordInputValidator = PasswordInputValidator()
    private val inputValidator = InputValidator()
    
    override fun onAttach(context: Context) {
        super.onAttach(context)
    
        (requireActivity().applicationContext as UsersApplication).apply {
            this@RegistrationFragment.navController = navController
            this@RegistrationFragment.usersRepository = usersRepository
        }
    }
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.btnRegister.setOnClickListener(this::register)
    }
    
    private fun register(view: View) = with(binding) {
        if (inputFieldsIsCorrect()) {
            val userName = etName.text.toString()
            val userLogin = etLogin.text.toString()
            val userPassword = etPassword.text.toString()
        
            val newUser = User(userName, userLogin, userPassword)
            usersRepository.addNewUser(newUser)
        
            Toast.makeText(
                requireContext(),
                getString(R.string.text_registration_user_added_successfully),
                Toast.LENGTH_SHORT
            ).show()
        
            backToLogin()
        }
    }
    
    private fun inputFieldsIsCorrect(): Boolean = with(binding) {
        nameInputLayout.error = if (!inputValidator.isFilled(etName.text.toString()))
            Error.InputError.EmptyFieldError.extract(requireContext())
        else
            null
        
        
        loginInputLayout.error = if (!inputValidator.isFilled(etLogin.text.toString()))
            Error.InputError.EmptyFieldError.extract(requireContext())
        else
            null
        
        passwordInputLayout.error = if (!passwordInputValidator.isNormalLength(etPassword.text.toString()))
            Error.InputError.PasswordLengthError.extract(requireContext())
        else
            null
        
        repeatedPasswordInputLayout.error = if (!passwordInputValidator.isPasswordsEquals(etPassword.text.toString(),etRepeatedPassword.text.toString()))
            Error.InputError.PasswordsDoNotMatchError.extract(requireContext())
        else if (!passwordInputValidator.isNormalLength(etRepeatedPassword.text.toString()))
            Error.InputError.PasswordLengthError.extract(requireContext())
        else
            null
        
        return (nameInputLayout.error == null &&
                loginInputLayout.error == null &&
                passwordInputLayout.error == null &&
                repeatedPasswordInputLayout.error == null)
        
    }
 
    private fun backToLogin() {
        navController.navigate(RegistrationFragmentDirections.actionRegistrationFragmentToLoginFragment())
    }
    
}
package com.ilya.loginandregistration.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ilya.core.model.User
import com.ilya.core.vaidator.InputValidator
import com.ilya.core.vaidator.PasswordInputValidator
import com.ilya.data.UsersRepository
import com.ilya.loginandregistration.R
import com.ilya.loginandregistration.databinding.FragmentRegistrationBinding
import com.ilya.loginandregistration.registration.navigation.RegistrationFragmentRouter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.ilya.loginandregistration.common.Error
import com.ilya.loginandregistration.common.extract

@AndroidEntryPoint
class RegistrationFragment : Fragment() {
    
    @Inject lateinit var registrationFragmentRouter: RegistrationFragmentRouter
    @Inject lateinit var usersRepository: UsersRepository
    
    private lateinit var binding: FragmentRegistrationBinding
    
    private val passwordInputValidator = PasswordInputValidator()
    private val inputValidator = InputValidator()
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    
        binding.btnRegister.setOnClickListener(this::register)
    }
    
    private fun register(view: View) = with(binding) {
        if (!inputFieldsIsCorrect()) return
        
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
        
        registrationFragmentRouter.backToLogin()
    }
    
    private fun inputFieldsIsCorrect(): Boolean = with(binding) {
        val name = etName.text.toString()
        val login = etLogin.text.toString()
        val password = etPassword.text.toString()
        val repeatedPassword = etRepeatedPassword.text.toString()
        
        nameInputLayout.error = if (inputValidator.isFilled(name)) {
            null
        } else {
            Error.RegistrationError.EmptyFieldError.extract(requireContext())
        }
        
        
        loginInputLayout.error = if (inputValidator.isFilled(login)) {
            null
        } else {
            Error.RegistrationError.EmptyFieldError.extract(requireContext())
        }
        
        passwordInputLayout.error = if (passwordInputValidator.isNormalLength(password)) {
            null
        } else {
            Error.RegistrationError.PasswordLengthError.extract(requireContext())
        }
        
        repeatedPasswordInputLayout.error = if (!passwordInputValidator.isPasswordsEquals(password, repeatedPassword)) {
            Error.RegistrationError.PasswordsDoNotMatchError.extract(requireContext())
        } else if (!passwordInputValidator.isNormalLength(repeatedPassword)) {
            Error.RegistrationError.PasswordLengthError.extract(requireContext())
        } else {
            null
        }
        
        return (nameInputLayout.error == null &&
                loginInputLayout.error == null &&
                passwordInputLayout.error == null &&
                repeatedPasswordInputLayout.error == null)
        
    }
    
}
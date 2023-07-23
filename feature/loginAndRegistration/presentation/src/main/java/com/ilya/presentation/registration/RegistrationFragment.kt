package com.ilya.presentation.registration

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ilya.core.models.User
import com.ilya.data.users.UsersRepository
import com.ilya.domain.error.RegistrationError
import com.ilya.domain.models.registration.RegisteringUserParams
import com.ilya.domain.usecases.registration.CheckInputFieldsUseCase
import com.ilya.domain.usecases.registration.RegisterNewUserUseCase
import com.ilya.presentation.R
import com.ilya.presentation.databinding.FragmentRegistrationBinding
import com.ilya.presentation.registration.navigation.RegistrationFragmentRouter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegistrationFragment : Fragment() {
    
    @Inject lateinit var registrationFragmentRouter: RegistrationFragmentRouter
    @Inject lateinit var usersRepository: UsersRepository
    
    private lateinit var binding: FragmentRegistrationBinding
    private lateinit var checkInputFieldsUseCase: CheckInputFieldsUseCase
    
    override fun onAttach(context: Context) {
        super.onAttach(context)
        
        RegistrationError.FieldIsEmpty.errorMessage = getString(R.string.text_registration_empty_field_error)
        RegistrationError.FieldsDoNotMatch.errorMessage = getString(R.string.text_registration_passwords_do_not_match_error)
        RegistrationError.ShortFieldLength.errorMessage = getString(R.string.text_registration_password_is_small_error)
    }
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.btnRegister.setOnClickListener(this::register)
    }
    
    private fun register(view: View) = with(binding) {
        if (inputFieldsIsCorrect()) {
            val name = etName.text.toString()
            val login = etLogin.text.toString()
            val password = etPassword.text.toString()
            
            val newUser = User(name, login, password)
            
            RegisterNewUserUseCase(newUser, usersRepository).execute()
            
            Toast.makeText(requireContext(), getString(R.string.text_registration_user_added_successfully), Toast.LENGTH_SHORT).show()
            
            registrationFragmentRouter.backToLogin()
        }
    }
    
    private fun inputFieldsIsCorrect(): Boolean = with(binding) {
        checkInputFields()
        return (
            nameInputLayout.error == null &&
            loginInputLayout.error == null &&
            passwordInputLayout.error == null &&
            repeatedPasswordInputLayout.error == null
        )
    }
    
    private fun checkInputFields() = with(binding) {
        val name = etName.text.toString()
        val login = etLogin.text.toString()
        val password = etPassword.text.toString()
        val repeatedPassword = etRepeatedPassword.text.toString()
    
        checkInputFieldsUseCase = CheckInputFieldsUseCase(RegisteringUserParams(name, login, password, repeatedPassword))
    
        val inputFieldsValidationResult = checkInputFieldsUseCase.execute()
    
        nameInputLayout.error = inputFieldsValidationResult.nameError?.errorMessage
        loginInputLayout.error = inputFieldsValidationResult.loginError?.errorMessage
        passwordInputLayout.error = inputFieldsValidationResult.passwordError?.errorMessage
        repeatedPasswordInputLayout.error = inputFieldsValidationResult.repeatedPasswordError?.errorMessage
    }
   
}
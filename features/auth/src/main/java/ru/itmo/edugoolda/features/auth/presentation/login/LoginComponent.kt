package ru.itmo.edugoolda.features.auth.presentation.login

import kotlinx.coroutines.flow.StateFlow
import ru.mobileup.kmm_form_validation.control.InputControl

interface LoginComponent {
    val emailInputControl: InputControl
    val passwordInputControl: InputControl
    val isLoginProgress: StateFlow<Boolean>

    fun onLoginClick()
    fun onRegisterRequestClick()

    interface Communication {
        fun onLoggedIn()
        fun onRegisterRequest()
    }
}

package ru.itmo.edugoolda.features.auth.presentation.register

import kotlinx.coroutines.flow.StateFlow
import ru.itmo.edugoolda.data.user.api.UserRole
import ru.mobileup.kmm_form_validation.control.InputControl

interface RegisterComponent {
    val emailInputControl: InputControl
    val passwordInputControl: InputControl
    val userNameInputControl: InputControl
    val selectedRole: StateFlow<UserRole>
    val isRegisterProgress: StateFlow<Boolean>

    fun onRegisterClick()
    fun onBackButtonClick()
    fun onUserRoleSelect(newRole: UserRole)

    interface Communication {
        fun onRegistered()
        fun onLoginRequest()
    }
}

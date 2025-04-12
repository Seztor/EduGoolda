package ru.itmo.edugoolda.features.auth.presentation.register

import kotlinx.coroutines.flow.StateFlow
import ru.itmo.edugoolda.data.user.api.UseRole
import ru.mobileup.kmm_form_validation.control.InputControl

interface RegisterComponent {
    val emailInputControl: InputControl
    val passwordInputControl: InputControl
    val userNameInputControl: InputControl
    val selectedRole: StateFlow<UseRole>
    val isRegisterProgress: StateFlow<Boolean>

    fun onRegisterClick()
    fun onNavigateToMainMenuClick()
    fun onUserRoleSelect(newRole: UseRole)

    interface Communication {
        fun onRegistered()
        fun onNavigateToMainMenu()
    }
}

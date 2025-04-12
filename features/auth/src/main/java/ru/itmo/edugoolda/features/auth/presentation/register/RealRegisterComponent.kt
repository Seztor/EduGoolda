package ru.itmo.edugoolda.features.auth.presentation.register

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.MutableStateFlow
import ru.itmo.edugoolda.core.error_handling.ErrorHandler
import ru.itmo.edugoolda.core.error_handling.safeLaunch
import ru.itmo.edugoolda.core.utils.componentScope
import ru.itmo.edugoolda.core.utils.withProgress
import ru.itmo.edugoolda.data.auth.api.AuthRepository
import ru.itmo.edugoolda.data.auth.api.domain.Email
import ru.itmo.edugoolda.data.auth.api.domain.Password
import ru.itmo.edugoolda.data.user.api.UseRole
import ru.mobileup.kmm_form_validation.control.InputControl

class RealRegisterComponent(
    componentContext: ComponentContext,
    private val communication: RegisterComponent.Communication,
    private val errorHandler: ErrorHandler,
    private val authRepository: AuthRepository
) : RegisterComponent, ComponentContext by componentContext {
    override val emailInputControl = InputControl(componentScope)
    override val passwordInputControl = InputControl(componentScope)
    override val userNameInputControl = InputControl(componentScope)
    override val selectedRole = MutableStateFlow(UseRole.Teacher)
    override val isRegisterProgress = MutableStateFlow(false)

    override fun onRegisterClick() {
        if (isRegisterProgress.value) return

        componentScope.safeLaunch(errorHandler) {
            withProgress(isRegisterProgress) {
                authRepository.register(
                    email = Email(emailInputControl.value.toString()),
                    password = Password(passwordInputControl.value.toString()),
                    name = userNameInputControl.value.toString(),
                    role = selectedRole.value.name
                )
                communication.onRegistered()
            }
        }
    }

    override fun onNavigateToMainMenuClick() {
        communication.onNavigateToMainMenu()
    }

    override fun onUserRoleSelect(newRole: UseRole) {
        selectedRole.value = newRole
    }
}
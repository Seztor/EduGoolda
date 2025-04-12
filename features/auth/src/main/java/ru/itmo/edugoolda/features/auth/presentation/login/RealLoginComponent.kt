package ru.itmo.edugoolda.features.auth.presentation.login

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.MutableStateFlow
import ru.itmo.edugoolda.core.error_handling.ErrorHandler
import ru.itmo.edugoolda.core.error_handling.safeLaunch
import ru.itmo.edugoolda.core.utils.componentScope
import ru.itmo.edugoolda.core.utils.withProgress
import ru.itmo.edugoolda.data.auth.api.AuthRepository
import ru.itmo.edugoolda.data.auth.api.domain.Email
import ru.itmo.edugoolda.data.auth.api.domain.Password
import ru.mobileup.kmm_form_validation.control.InputControl

class RealLoginComponent(
    componentContext: ComponentContext,
    private val communication: LoginComponent.Communication,
    private val errorHandler: ErrorHandler,
    private val authRepository: AuthRepository
) : LoginComponent, ComponentContext by componentContext {
    override val emailInputControl = InputControl(componentScope)
    override val passwordInputControl = InputControl(componentScope)
    override val isLoginProgress = MutableStateFlow(false)

    override fun onLoginClick() {
        if (isLoginProgress.value) return

        componentScope.safeLaunch(errorHandler) {
            withProgress(isLoginProgress) {
                authRepository.login(
                    email = Email(emailInputControl.value.toString()),
                    password = Password(passwordInputControl.value.toString())
                )
                communication.onLoggedIn()
            }
        }
    }

    override fun onNavigateToRegisterClick() {
        communication.onNavigateToRegister()
    }
}
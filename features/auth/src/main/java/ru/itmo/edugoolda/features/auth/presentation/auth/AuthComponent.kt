package ru.itmo.edugoolda.features.auth.presentation.auth

import com.arkivanov.decompose.router.stack.ChildStack
import kotlinx.coroutines.flow.StateFlow
import ru.itmo.edugoolda.features.auth.presentation.login.LoginComponent
import ru.itmo.edugoolda.features.auth.presentation.register.RegisterComponent

interface AuthComponent {

    val childStack: StateFlow<ChildStack<*, Child>>

    sealed interface Child {
        class Login(val instance: LoginComponent) : Child
        class Register(val instance: RegisterComponent) : Child
    }
}
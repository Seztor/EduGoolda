package ru.itmo.edugoolda.features.auth.presentation.login

import com.arkivanov.decompose.router.stack.ChildStack
import kotlinx.coroutines.flow.StateFlow
import ru.itmo.edugoolda.features.auth.presentation.create.login.LoginCreateComponent


interface LoginComponent {

    val childStack: StateFlow<ChildStack<*, Child>>

    sealed interface Child {
        class Login(val instance: LoginCreateComponent) : Child
    }
}
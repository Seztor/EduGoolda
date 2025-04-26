package ru.itmo.edugoolda.features.root.presentation

import com.arkivanov.decompose.router.stack.ChildStack
import kotlinx.coroutines.flow.StateFlow
import ru.itmo.edugoolda.core.message.presentation.MessageComponent
import ru.itmo.edugoolda.features.auth.presentation.auth.AuthComponent

/**
 * A root of a Decompose component tree.
 *
 * Note: Try to minimize child count in RootComponent. It should operate by flows of screens rather than separate screens.
 */
interface RootComponent {

    val childStack: StateFlow<ChildStack<*, Child>>

    val messageComponent: MessageComponent

    sealed interface Child {
        class Auth(val instance: AuthComponent) : Child
    }
}

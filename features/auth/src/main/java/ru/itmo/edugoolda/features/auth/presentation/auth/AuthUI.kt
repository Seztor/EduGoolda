package ru.itmo.edugoolda.features.auth.presentation.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children

@Suppress("ModifierReused")
@Composable
fun RootUi(
    component: AuthComponent,
    modifier: Modifier = Modifier
) {
    val childStack by component.childStack.collectAsState()

    Children(childStack, modifier) { child ->
        when (val instance = child.instance) {
            is AuthComponent.Child.Login -> TODO()
            is AuthComponent.Child.Register -> TODO()
            else -> TODO()
        }
    }
}
package ru.itmo.edugoolda.features.root

import com.arkivanov.decompose.ComponentContext
import org.koin.core.component.get
import ru.itmo.edugoolda.core.ComponentFactory
import ru.itmo.edugoolda.features.auth.presentation.auth.AuthComponent
import ru.itmo.edugoolda.features.auth.presentation.auth.RealAuthComponent
import ru.itmo.edugoolda.features.root.presentation.RealRootComponent
import ru.itmo.edugoolda.features.root.presentation.RootComponent

fun ComponentFactory.createRootComponent(componentContext: ComponentContext): RootComponent {
    return RealRootComponent(componentContext, get())
}

fun ComponentFactory.createAuthComponent(
    componentContext: ComponentContext,
    communication: AuthComponent.Communication,
): AuthComponent {
    return RealAuthComponent(componentContext, communication, get())
}
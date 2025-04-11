package ru.itmo.edugoolda.features.auth

import com.arkivanov.decompose.ComponentContext
import org.koin.core.component.get
import ru.itmo.edugoolda.core.ComponentFactory
import ru.itmo.edugoolda.features.auth.presentation.create.login.LoginCreateComponent
import ru.itmo.edugoolda.features.auth.presentation.create.login.RealLoginCreateComponent

fun ComponentFactory.createLoginCreateComponent(
    componentContext: ComponentContext,
    communication: LoginCreateComponent.Communication,
): RealLoginCreateComponent {
    return RealLoginCreateComponent(componentContext, communication, get(), get())
}
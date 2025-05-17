package ru.itmo.edugoolda.features.profile

import com.arkivanov.decompose.ComponentContext
import ru.itmo.edugoolda.core.ComponentFactory
import ru.itmo.edugoolda.features.profile.presentation.viewProfile.RealProfileComponent
import org.koin.core.component.get

fun ComponentFactory.createProfileComponent(
    componentContext: ComponentContext
): RealProfileComponent{
    return RealProfileComponent(componentContext, get(), get())
}


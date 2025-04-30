package ru.itmo.edugoolda.features.profile

import com.arkivanov.decompose.ComponentContext
import org.koin.core.component.get
import ru.itmo.edugoolda.core.ComponentFactory
import ru.itmo.edugoolda.features.profile.presentation.editPassword.RealEditPasswordComponent
import ru.itmo.edugoolda.features.profile.presentation.editProfile.RealEditProfileComponent
import ru.itmo.edugoolda.features.profile.presentation.viewProfile.RealProfileComponent

fun ComponentFactory.createProfileComponent(
    componentContext: ComponentContext,
): RealProfileComponent {
    return RealProfileComponent(componentContext, get(), get())
}

fun ComponentFactory.createEditProfileComponent(): RealEditProfileComponent{
    return RealEditProfileComponent(
        componentContext = TODO()
    )
}

fun ComponentFactory.createEditPasswordComponent(): RealEditPasswordComponent{
    return RealEditPasswordComponent()
}

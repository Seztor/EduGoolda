package ru.itmo.edugoolda.features.profile

import ru.itmo.edugoolda.core.ComponentFactory
import ru.itmo.edugoolda.features.profile.presentation.editPassword.RealEditPasswordComponent
import ru.itmo.edugoolda.features.profile.presentation.editProfile.RealEditProfileComponent
import ru.itmo.edugoolda.features.profile.presentation.viewProfile.RealProfileComponent

fun ComponentFactory.createProfileComponent(): RealProfileComponent{
    return RealProfileComponent()
}

fun ComponentFactory.createEditProfileComponent(): RealEditProfileComponent{
    return RealEditProfileComponent(
        componentContext = TODO()
    )
}

fun ComponentFactory.createEditPasswordComponent(): RealEditPasswordComponent{
    return RealEditPasswordComponent()
}

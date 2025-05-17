package ru.itmo.edugoolda.features.profile

import com.arkivanov.decompose.ComponentContext
import ru.itmo.edugoolda.core.ComponentFactory
import ru.itmo.edugoolda.features.profile.presentation.viewProfile.RealProfileComponent
import org.koin.core.component.get
import ru.itmo.edugoolda.data.user.api.UserId

fun ComponentFactory.createProfileComponent(
    userId: UserId,
    componentContext: ComponentContext
): RealProfileComponent{
    return RealProfileComponent(userId, componentContext, get(), get())
}


package ru.itmo.edugoolda.features.profile

import com.arkivanov.decompose.ComponentContext
import org.koin.core.component.get
import ru.itmo.edugoolda.core.ComponentFactory
import ru.itmo.edugoolda.data.user.api.UserId
import ru.itmo.edugoolda.features.profile.presentation.viewProfile.RealProfileComponent

fun ComponentFactory.createProfileComponent(
    componentContext: ComponentContext,
    userId: UserId?
): RealProfileComponent {
    return RealProfileComponent(userId, componentContext, get(), get())
}
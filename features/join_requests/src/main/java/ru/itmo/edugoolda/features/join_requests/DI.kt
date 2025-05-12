package ru.itmo.edugoolda.features.join_requests

import com.arkivanov.decompose.ComponentContext
import org.koin.core.component.get
import ru.itmo.edugoolda.core.ComponentFactory
import ru.itmo.edugoolda.features.join_requests.presentation.JoinRequestsComponentImpl

fun ComponentFactory.createInvitationComponent(
    componentContext: ComponentContext,
): JoinRequestsComponentImpl {
    return JoinRequestsComponentImpl(componentContext, get(), get())
}

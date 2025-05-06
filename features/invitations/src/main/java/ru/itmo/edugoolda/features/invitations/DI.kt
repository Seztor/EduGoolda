package ru.itmo.edugoolda.features.invitations

import com.arkivanov.decompose.ComponentContext
import org.koin.core.component.get
import ru.itmo.edugoolda.core.ComponentFactory
import ru.itmo.edugoolda.features.invitations.presentation.InvitationsComponentImpl

fun ComponentFactory.createInvitationComponent(
    componentContext: ComponentContext,
): InvitationsComponentImpl {
    return InvitationsComponentImpl(componentContext, get(), get(), get())
}

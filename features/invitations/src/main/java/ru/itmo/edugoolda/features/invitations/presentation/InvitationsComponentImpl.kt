package ru.itmo.edugoolda.features.invitations.presentation

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.StateFlow
import ru.itmo.edugoolda.core.error_handling.ErrorHandler
import ru.itmo.edugoolda.core.utils.PagedState
import ru.itmo.edugoolda.core.utils.observe
import ru.itmo.edugoolda.data.invitations.api.InvitationList
import ru.itmo.edugoolda.data.invitations.api.InvitationListRepository

class InvitationsComponentImpl(
    componentContext: ComponentContext,
    invitationsRepository: InvitationListRepository,
    errorHandler: ErrorHandler
) : InvitationsComponent, ComponentContext by componentContext {
    private val invitationReplica = invitationsRepository.invitationListReplica
    override val invitationState = invitationReplica.observe(this,errorHandler)
    override fun onRefresh() {
        invitationReplica.refresh()
    }

    override fun onRetryClick() {
        invitationReplica.revalidate()
    }

    override fun onLoadNext() {
        invitationReplica.loadNext()
    }

}
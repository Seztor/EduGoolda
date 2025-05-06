package ru.itmo.edugoolda.features.invitations.presentation

import com.arkivanov.decompose.ComponentContext
import ru.itmo.edugoolda.core.error_handling.ErrorHandler
import ru.itmo.edugoolda.core.error_handling.safeLaunch
import ru.itmo.edugoolda.core.utils.componentScope
import ru.itmo.edugoolda.core.utils.observe
import ru.itmo.edugoolda.data.invitations.api.Invitation
import ru.itmo.edugoolda.data.invitations.api.InvitationListRepository
import ru.itmo.edugoolda.data.invitations.api.InvitationRepository

class InvitationsComponentImpl(
    componentContext: ComponentContext,
    invitationListRepository: InvitationListRepository,
    private val errorHandler: ErrorHandler,
    private val invitationRepository: InvitationRepository,

    ) : InvitationsComponent, ComponentContext by componentContext {

    private val invitationReplica = invitationListRepository.invitationListReplica
    override val invitationState = invitationReplica.observe(this, errorHandler)
    override fun onAcceptClick(invitation: Invitation) {
        componentScope.safeLaunch(errorHandler) {
            invitationRepository.action(invitation.id, "accept")
        }
    }

    override fun onDeclineClick(invitation: Invitation) {
        componentScope.safeLaunch(errorHandler) {
            invitationRepository.action(invitation.id, "decline")
        }
    }

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
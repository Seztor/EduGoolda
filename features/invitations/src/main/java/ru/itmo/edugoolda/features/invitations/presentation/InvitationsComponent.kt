package ru.itmo.edugoolda.features.invitations.presentation

import kotlinx.coroutines.flow.StateFlow
import ru.itmo.edugoolda.core.utils.PagedState
import ru.itmo.edugoolda.data.invitations.api.Invitation
import ru.itmo.edugoolda.data.invitations.api.InvitationList

interface InvitationsComponent {
    val invitationState: StateFlow<PagedState<InvitationList>>

    fun onAcceptClick(invitation: Invitation)
    fun onDeclineClick(invitation: Invitation)
    fun onRefresh()
    fun onRetryClick()
    fun onLoadNext()
}
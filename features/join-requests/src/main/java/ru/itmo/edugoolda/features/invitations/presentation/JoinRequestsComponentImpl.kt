package ru.itmo.edugoolda.features.invitations.presentation

import com.arkivanov.decompose.ComponentContext
import ru.itmo.edugoolda.core.error_handling.ErrorHandler
import ru.itmo.edugoolda.core.error_handling.safeLaunch
import ru.itmo.edugoolda.core.utils.componentScope
import ru.itmo.edugoolda.core.utils.observe
import ru.itmo.edugoolda.data.invitations.api.JoinRequest
import ru.itmo.edugoolda.data.invitations.api.JoinRequestAction
import ru.itmo.edugoolda.data.invitations.api.JoinRequestListRepository
import ru.itmo.edugoolda.data.invitations.api.JoinRequestRepository

class JoinRequestsComponentImpl(
    componentContext: ComponentContext,
    joinRequestListRepository: JoinRequestListRepository,
    private val errorHandler: ErrorHandler,
    private val joinRequestRepository: JoinRequestRepository,

    ) : JoinRequestsComponent, ComponentContext by componentContext {

    private val invitationReplica = joinRequestListRepository.joinRequestListReplica
    override val joinRequestState = invitationReplica.observe(this, errorHandler)
    override fun onAcceptClick(joinRequest: JoinRequest) {
        componentScope.safeLaunch(errorHandler) {
            joinRequestRepository.respondToJoinRequest(joinRequest.id, JoinRequestAction.Accept)
        }
    }

    override fun onDeclineClick(joinRequest: JoinRequest) {
        componentScope.safeLaunch(errorHandler) {
            joinRequestRepository.respondToJoinRequest(joinRequest.id, JoinRequestAction.Decline)
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
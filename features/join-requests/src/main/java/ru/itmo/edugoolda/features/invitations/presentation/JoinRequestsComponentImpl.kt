package ru.itmo.edugoolda.features.invitations.presentation

import com.arkivanov.decompose.ComponentContext
import me.aartikov.replica.algebra.normal.combine
import me.aartikov.replica.algebra.normal.map
import me.aartikov.replica.algebra.paged.map
import me.aartikov.replica.algebra.paged.toReplica
import ru.itmo.edugoolda.core.error_handling.ErrorHandler
import ru.itmo.edugoolda.core.error_handling.safeLaunch
import ru.itmo.edugoolda.core.utils.componentScope
import ru.itmo.edugoolda.core.utils.observe
import ru.itmo.edugoolda.data.invitations.api.JoinRequest
import ru.itmo.edugoolda.data.invitations.api.JoinRequestAction
import ru.itmo.edugoolda.data.invitations.api.JoinRequestRepository

class JoinRequestsComponentImpl(
    componentContext: ComponentContext,
    private val errorHandler: ErrorHandler,
    private val joinRequestRepository: JoinRequestRepository,

    ) : JoinRequestsComponent, ComponentContext by componentContext {

    private val invitationReplica = joinRequestRepository.joinRequestListReplica
    override val joinRequestState = invitationReplica.observe(this, errorHandler)
    val a = null
    val mainJoinRequestsReplica = invitationReplica.toReplica().map { v -> v.joinRequestList.take(3)}
    val mainStateReplica = combine(
        mainJoinRequestsReplica,
        invitationReplica.toReplica()
    ) {mainJoinRequests, inv -> listOf(mainJoinRequests, inv) }
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
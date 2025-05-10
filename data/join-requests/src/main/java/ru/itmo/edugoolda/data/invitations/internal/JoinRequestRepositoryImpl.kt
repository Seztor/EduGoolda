package ru.itmo.edugoolda.data.invitations.internal

import ru.itmo.edugoolda.data.invitations.api.JoinRequestRepository
import ru.itmo.edugoolda.data.invitations.api.JoinRequestAction
import ru.itmo.edugoolda.data.invitations.api.JoinRequestId
import ru.itmo.edugoolda.data.invitations.internal.dto.JoinRequestResponseRequest

class JoinRequestRepositoryImpl(
    private val api: JoinRequestApi,
) : JoinRequestRepository {

    override suspend fun respondToJoinRequest(
        joinRequestId: JoinRequestId,
        action: JoinRequestAction
    ) {
        val joinRequestResponseRequest = when (action) {
            JoinRequestAction.Accept -> JoinRequestResponseRequest("accept")
            JoinRequestAction.Decline -> JoinRequestResponseRequest("decline")
            JoinRequestAction.DeclineAndBan -> JoinRequestResponseRequest("decline_and_ban")
        }
        api.respondToJoinRequest(joinRequestId.value, joinRequestResponseRequest)
    }
}
package ru.itmo.edugoolda.data.invitations.api

import me.aartikov.replica.paged.PagedReplica

interface JoinRequestRepository {
    suspend fun respondToJoinRequest(joinRequestId: JoinRequestId, action: JoinRequestAction)
    val joinRequestListReplica: PagedReplica<JoinRequestList>
}
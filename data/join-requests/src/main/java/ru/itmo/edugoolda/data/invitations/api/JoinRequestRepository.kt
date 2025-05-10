package ru.itmo.edugoolda.data.invitations.api

interface JoinRequestRepository {
    suspend fun respondToJoinRequest(joinRequestId: JoinRequestId, action: JoinRequestAction)
}
package ru.itmo.edugoolda.data.invitations.internal


import ru.itmo.edugoolda.data.invitations.api.InvitationRepository
import ru.itmo.edugoolda.data.invitations.internal.dto.InvitationActionRequest

class InvitationRepositoryImpl(
    private val api: InvitationApi,
) : InvitationRepository {

    override suspend fun action(invitationId: String, action: String) {
        val invitationActionRequest = when (action) {
            "accept" -> InvitationActionRequest("accept")
            "decline" -> InvitationActionRequest("decline")
            else -> InvitationActionRequest("decline_and_ban")
        }
        api.action(invitationId, invitationActionRequest)
    }


}
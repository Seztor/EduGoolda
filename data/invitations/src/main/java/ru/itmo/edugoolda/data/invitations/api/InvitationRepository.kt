package ru.itmo.edugoolda.data.invitations.api

interface InvitationRepository {
    suspend fun action(invitationId: String, action: String)
}
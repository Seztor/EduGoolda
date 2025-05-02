package ru.itmo.edugoolda.data.invitations.api


data class InvitationList (
    val invitationList: List<Invitation>,
    val hasNextPage: Boolean

)
package ru.itmo.edugoolda.data.invitations.api

import kotlinx.serialization.Serializable


data class InvitationList (
    val invitationList: List<Invitation>,
    val hasNextPage: Boolean

)
package ru.itmo.edugoolda.data.invitations.internal.domain

import ru.itmo.edugoolda.data.invitations.api.Invitation

data class InvitationListWithTotal(
    val invitationList: List<Invitation>,
    val total: Int
)

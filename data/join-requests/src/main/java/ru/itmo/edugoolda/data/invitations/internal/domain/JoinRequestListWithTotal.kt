package ru.itmo.edugoolda.data.invitations.internal.domain

import ru.itmo.edugoolda.data.invitations.api.JoinRequest

data class JoinRequestListWithTotal(
    val joinRequestList: List<JoinRequest>,
    val total: Int
)

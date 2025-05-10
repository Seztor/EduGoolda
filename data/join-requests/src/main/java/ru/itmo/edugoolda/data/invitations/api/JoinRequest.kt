package ru.itmo.edugoolda.data.invitations.api

import ru.itmo.edugoolda.data.user.api.UserInfo

data class JoinRequest(
    val id: JoinRequestId,
    val groupName: String,
    val sender: UserInfo,
    val date: String
)
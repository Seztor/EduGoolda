package ru.itmo.edugoolda.data.invitations.api


data class JoinRequestList (
    val joinRequestList: List<JoinRequest>,
    val hasNextPage: Boolean
)
package ru.itmo.edugoolda.data.join_requests.api


data class JoinRequestList (
    val joinRequestList: List<JoinRequest>,
    val hasNextPage: Boolean
)
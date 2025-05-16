package ru.itmo.edugoolda.data.join_requests.internal.domain

import ru.itmo.edugoolda.data.join_requests.api.JoinRequest

data class JoinRequestListWithTotal(
    val joinRequestList: List<JoinRequest>,
    val total: Int
)

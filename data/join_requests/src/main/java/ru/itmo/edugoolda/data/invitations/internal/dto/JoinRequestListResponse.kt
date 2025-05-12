package ru.itmo.edugoolda.data.invitations.internal.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

import ru.itmo.edugoolda.data.invitations.api.JoinRequest
import ru.itmo.edugoolda.data.invitations.api.JoinRequestId

import ru.itmo.edugoolda.data.invitations.internal.domain.JoinRequestListWithTotal
import ru.itmo.edugoolda.data.user.internal.dto.UserInfoDTO
import ru.itmo.edugoolda.data.user.internal.dto.toDomain

@Serializable
data class JoinRequestListResponse(
    @SerialName("request") val joinRequestList: List<JoinRequestDTO>,
    @SerialName("total") val total: Int,
)

fun JoinRequestListResponse.toDomain(): JoinRequestListWithTotal = JoinRequestListWithTotal(
    joinRequestList = joinRequestList.map { it.toDomain() },
    total = total
)

@Serializable
data class JoinRequestDTO(
    @SerialName("id") val id: String,
    @SerialName("group_name") val groupName: String,
    @SerialName("sender") val sender: UserInfoDTO,
    @SerialName("date") val date: String
)

fun JoinRequestDTO.toDomain(): JoinRequest = JoinRequest(
    id = JoinRequestId(id),
    groupName = groupName,
    sender = sender.toDomain(),
    date = date
)
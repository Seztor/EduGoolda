package ru.itmo.edugoolda.data.invitations.internal.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.itmo.edugoolda.data.invitations.api.Invitation

import ru.itmo.edugoolda.data.invitations.internal.domain.InvitationListWithTotal
import ru.itmo.edugoolda.data.user.internal.dto.UserInfoDTO
import ru.itmo.edugoolda.data.user.internal.dto.toDomain

@Serializable
data class InvitationListResponse(
    @SerialName("request") val invitatonList: List<InvitationDTO>,
    @SerialName("total") val total: Int,
    )

fun InvitationListResponse.toDomain(): InvitationListWithTotal = InvitationListWithTotal(
    invitationList = invitatonList.map { it.toDomain() },
    total = total
)

@Serializable
data class InvitationDTO(
    @SerialName("id") val id: String,
    @SerialName("sender") val sender: UserInfoDTO
)

fun InvitationDTO.toDomain(): Invitation = Invitation(
    id = id,
    sender = sender.toDomain()
)
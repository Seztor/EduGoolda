package ru.itmo.edugoolda.data.group.groupInvitationData.internal.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.itmo.edugoolda.data.group.groupInvitationData.api.GroupInvitationCode
import ru.itmo.edugoolda.data.group.groupInvitationData.api.GroupInvitationData

@Serializable
internal data class GroupInvitationDataDTO(
    @SerialName("code") val code: String,
    @SerialName("invitation_link") val invitationLink: String
)

internal fun GroupInvitationDataDTO.toDomain(): GroupInvitationData = GroupInvitationData(
    code = GroupInvitationCode(code),
    invitationLink = invitationLink
)
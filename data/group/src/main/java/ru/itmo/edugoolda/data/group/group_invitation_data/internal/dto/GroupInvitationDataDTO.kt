package ru.itmo.edugoolda.data.group.group_invitation_data.internal.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.itmo.edugoolda.data.group.group_invitation_data.api.GroupInvitationCode
import ru.itmo.edugoolda.data.group.group_invitation_data.api.GroupInvitationData

@Serializable
internal data class GroupInvitationDataDTO(
    @SerialName("code") val code: String,
    @SerialName("invitation_link") val invitationLink: String
)

internal fun GroupInvitationDataDTO.toDomain(): GroupInvitationData = GroupInvitationData(
    code = GroupInvitationCode(code),
    invitationLink = invitationLink
)
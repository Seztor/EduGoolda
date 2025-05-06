package ru.itmo.edugoolda.data.invitations.internal.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InvitationActionRequest(
    @SerialName("action") val action: String
)
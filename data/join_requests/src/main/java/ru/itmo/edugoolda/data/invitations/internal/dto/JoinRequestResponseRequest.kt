package ru.itmo.edugoolda.data.invitations.internal.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JoinRequestResponseRequest(
    @SerialName("action") val action: String
)
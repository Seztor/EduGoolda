package ru.itmo.edugoolda.data.group.create_group.internal.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class CreateGroupRequest(
    @SerialName("name") val name: String,
    @SerialName("description") val description: String?,
    @SerialName("subject_id") val subjectId: String
)

@Serializable
internal data class CreateSubjectRequest(
    @SerialName("name") val name: String
)
package ru.itmo.edugoolda.data.user.internal.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.itmo.edugoolda.data.user.api.UserInfo


@Serializable
data class UserInfoDTO(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("email") val email: String,
    @SerialName("role") val role: String,
    @SerialName("is_deleted") val isDeleted: Boolean
)

fun UserInfoDTO.toDomain(): UserInfo = UserInfo(
    id = id,
    name = name,
    email = email,
    role = role,
    isDeleted = isDeleted
)

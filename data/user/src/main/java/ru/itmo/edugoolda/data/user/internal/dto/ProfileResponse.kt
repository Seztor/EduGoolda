package ru.itmo.edugoolda.data.user.internal.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.itmo.edugoolda.data.user.api.Profile
import ru.itmo.edugoolda.data.user.api.UserId
import ru.itmo.edugoolda.data.user.internal.mappers.UserRoleMapper

@Serializable
data class ProfileResponse(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("email") val email: String,
    @SerialName("role") val role: String,
    @SerialName("is_deleted") val isDeleted: Boolean,
    @SerialName("bio") val bio: String?
)

fun ProfileResponse.toDomain(): Profile = Profile(
    name = name,
    email = email,
    bio = bio.orEmpty(),
    role = UserRoleMapper.fromString(role),
    id = UserId(id),
)

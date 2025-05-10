package ru.itmo.edugoolda.data.user.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class UserInfo(
    val id: UserId,
    val name: String,
    val email: String,
    val role: UserRole,
    val isDeleted: Boolean
)



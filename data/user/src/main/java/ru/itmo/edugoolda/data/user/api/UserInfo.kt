package ru.itmo.edugoolda.data.user.api

data class UserInfo(
    val id: UserId,
    val name: String,
    val email: String,
    val role: UserRole,
    val isDeleted: Boolean
)
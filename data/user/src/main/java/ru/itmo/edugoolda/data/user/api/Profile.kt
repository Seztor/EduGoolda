package ru.itmo.edugoolda.data.user.api

data class Profile(
    val name: String,
    val email: String,
    val bio: String,
    val role: UserRole,
    val id: UserId,
)
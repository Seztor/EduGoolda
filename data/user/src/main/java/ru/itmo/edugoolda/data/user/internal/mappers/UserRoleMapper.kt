package ru.itmo.edugoolda.data.user.internal.mappers

import ru.itmo.edugoolda.data.user.api.UserRole

object UserRoleMapper {
    fun fromString(data: String): UserRole {
        return when (data) {
            "student" -> UserRole.Student
            else -> UserRole.Teacher
        }
    }
}
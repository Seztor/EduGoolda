package ru.itmo.edugoolda.data.group.groupOfStudentsList.internal.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.itmo.edugoolda.data.group.groupOfStudentsList.internal.domain.GroupOfStudentsListWithTotal
import ru.itmo.edugoolda.data.user.internal.dto.UserInfoDTO
import ru.itmo.edugoolda.data.user.internal.dto.toDomain

@Serializable
internal class GroupOfStudentsResponse(
    @SerialName("users") val users: List<UserInfoDTO>,
    @SerialName("total") val total: Int
)

internal fun GroupOfStudentsResponse.toDomain(): GroupOfStudentsListWithTotal = GroupOfStudentsListWithTotal(
    users = users.map { it.toDomain() },
    total = total,
)
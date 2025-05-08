package ru.itmo.edugoolda.data.group.group_of_students_list.internal.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.itmo.edugoolda.data.group.group_of_students_list.internal.domain.GroupOfStudentsListWithTotal
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

@Serializable
internal data class KickStudentRequest(
    @SerialName("action") val action: String,
    @SerialName("group_id") val groupId: String,
    @SerialName("student_id") val studentId: String
)
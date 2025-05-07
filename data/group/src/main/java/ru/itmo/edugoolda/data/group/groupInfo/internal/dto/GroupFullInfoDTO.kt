package ru.itmo.edugoolda.data.group.groupInfo.internal.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.itmo.edugoolda.data.group.groupInfo.api.GroupFullInfo
import ru.itmo.edugoolda.data.group.groupList.api.GroupId
import ru.itmo.edugoolda.data.group.groupInfo.api.GroupSubject
import ru.itmo.edugoolda.data.group.groupInfo.api.SubjectId
import ru.itmo.edugoolda.data.user.internal.dto.UserInfoDTO
import ru.itmo.edugoolda.data.user.internal.dto.toDomain

@Serializable
internal data class GroupFullInfoDTO(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("description") val description: String?,
    @SerialName("subject") val subject: GroupSubjectDTO,
    @SerialName("owner") val owner: UserInfoDTO,
    @SerialName("students_count") val studentsCount: Int,
    @SerialName("requests_count") val requestsCount: Int,
    @SerialName("banned_count") val bannedCount: Int,
    @SerialName("new_solutions_count") val newSolutionsCount: Int,
    @SerialName("tasks_count") val tasksCount: Int,
    @SerialName("is_active") val isActive: Boolean,
    @SerialName("created_at") val createdAt: String
)

internal fun GroupFullInfoDTO.toDomain(): GroupFullInfo = GroupFullInfo(
    id = GroupId(id),
    name = name,
    description = description,
    subject = subject.toDomain(),
    owner = owner.toDomain(),
    studentsCount = studentsCount,
    requestsCount = requestsCount,
    bannedCount = bannedCount,
    newSolutionsCount = newSolutionsCount,
    tasksCount = tasksCount,
    isActive = isActive,
    createdAt = createdAt
)

@Serializable
internal data class GroupSubjectDTO(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String
)

internal fun GroupSubjectDTO.toDomain(): GroupSubject = GroupSubject(
    id = SubjectId(id),
    name = name
)
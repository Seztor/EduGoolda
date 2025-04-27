package ru.itmo.edugoolda.data.group.studentGroups.internal.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.itmo.edugoolda.data.group.studentGroups.api.StudentGroupId
import ru.itmo.edugoolda.data.group.studentGroups.api.StudentGroupInfo
import ru.itmo.edugoolda.data.group.studentGroups.internal.domain.StudentsGroupWithTotal

@Serializable
data class StudentGroupsResponse(
    @SerialName("groups") val groups: List<StudentGroupInfoDTO>,
    @SerialName("total") val total: Int
)

fun StudentGroupsResponse.toDomain(): StudentsGroupWithTotal = StudentsGroupWithTotal(
    groups = groups.map { it.toDomain() },
    total = total,
)

@Serializable
data class StudentGroupInfoDTO(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("subject_name") val subjectName: String,
    @SerialName("owner_name") val ownerName: String
)

fun StudentGroupInfoDTO.toDomain(): StudentGroupInfo = StudentGroupInfo(
    id = StudentGroupId(id),
    name = name,
    subjectName = subjectName,
    ownerName = ownerName
)
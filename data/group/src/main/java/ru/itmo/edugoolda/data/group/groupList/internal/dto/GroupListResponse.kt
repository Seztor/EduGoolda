package ru.itmo.edugoolda.data.group.groupList.internal.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.itmo.edugoolda.data.group.groupList.api.GroupId
import ru.itmo.edugoolda.data.group.groupInfo.api.GroupInfo
import ru.itmo.edugoolda.data.group.groupList.internal.domain.GroupListWithTotal

@Serializable
internal class GroupListResponse(
    @SerialName("groups") val groups: List<GroupInfoDTO>,
    @SerialName("total") val total: Int
)

internal fun GroupListResponse.toDomain(): GroupListWithTotal = GroupListWithTotal(
    groups = groups.map { it.toDomain() },
    total = total,
)

@Serializable
internal data class GroupInfoDTO(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("subject_name") val subjectName: String,
    @SerialName("owner_name") val ownerName: String,
    @SerialName("is_favourite") val isFavourite: Boolean
)

internal fun GroupInfoDTO.toDomain(): GroupInfo = GroupInfo(
    id = GroupId(id),
    name = name,
    subjectName = subjectName,
    ownerName = ownerName,
    isFavourite = isFavourite
)
package ru.itmo.edugoolda.data.group.groupInfo.api

import ru.itmo.edugoolda.data.group.groupList.api.GroupId

data class GroupInfo(
    val id: GroupId,
    val name: String,
    val subjectName: String,
    val ownerName: String,
    val isFavourite: Boolean
)

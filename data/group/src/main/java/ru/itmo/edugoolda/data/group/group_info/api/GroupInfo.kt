package ru.itmo.edugoolda.data.group.group_info.api

import ru.itmo.edugoolda.data.group.group_list.api.GroupId

data class GroupInfo(
    val id: GroupId,
    val name: String,
    val subjectName: String,
    val ownerName: String,
    val isFavourite: Boolean
)

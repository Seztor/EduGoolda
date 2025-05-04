package ru.itmo.edugoolda.data.group.groupList.api

data class GroupItemInfo(
    val id: GroupId,
    val name: String,
    val subjectName: String,
    val ownerName: String
)

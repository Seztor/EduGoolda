package ru.itmo.edugoolda.data.group.groupList.internal.domain

import ru.itmo.edugoolda.data.group.groupInfo.api.GroupInfo

internal data class GroupListWithTotal(
    val groups: List<GroupInfo>,
    val total: Int,
)
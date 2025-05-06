package ru.itmo.edugoolda.data.group.groupList.internal.domain

import ru.itmo.edugoolda.data.group.groupList.api.GroupItemInfo

internal data class GroupListWithTotal(
    val groups: List<GroupItemInfo>,
    val total: Int,
)
package ru.itmo.edugoolda.data.group.group_list.internal.domain

import ru.itmo.edugoolda.data.group.group_info.api.GroupInfo

internal data class GroupListWithTotal(
    val groups: List<GroupInfo>,
    val total: Int,
)
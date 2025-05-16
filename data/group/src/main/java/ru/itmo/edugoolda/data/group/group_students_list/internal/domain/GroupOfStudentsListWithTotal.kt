package ru.itmo.edugoolda.data.group.group_students_list.internal.domain

import ru.itmo.edugoolda.data.user.api.UserInfo

internal data class GroupOfStudentsListWithTotal(
    val users: List<UserInfo>,
    val total: Int,
)
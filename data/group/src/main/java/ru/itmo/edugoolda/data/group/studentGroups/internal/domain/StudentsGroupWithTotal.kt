package ru.itmo.edugoolda.data.group.studentGroups.internal.domain

import ru.itmo.edugoolda.data.group.studentGroups.api.StudentGroupInfo

data class StudentsGroupWithTotal(
    val groups: List<StudentGroupInfo>,
    val total: Int,
)
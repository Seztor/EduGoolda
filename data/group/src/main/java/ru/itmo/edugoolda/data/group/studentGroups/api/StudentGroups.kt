package ru.itmo.edugoolda.data.group.studentGroups.api

data class StudentGroups(
    val groups: List<StudentGroupInfo>,
    val hasNextPage: Boolean
)

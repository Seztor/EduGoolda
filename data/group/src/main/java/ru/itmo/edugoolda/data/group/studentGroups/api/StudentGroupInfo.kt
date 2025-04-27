package ru.itmo.edugoolda.data.group.studentGroups.api

data class StudentGroupInfo(
    val id: StudentGroupId,
    val name: String,
    val subjectName: String,
    val ownerName: String
)

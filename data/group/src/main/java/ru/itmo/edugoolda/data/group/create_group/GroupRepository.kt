package ru.itmo.edugoolda.data.group.create_group

interface GroupRepository {
    suspend fun createGroup(selectedSubject: String, name: String, description: String): String
}
package ru.itmo.edugoolda.data.group.create_group.api

import ru.itmo.edugoolda.data.group.group_info.api.GroupFullInfo
import ru.itmo.edugoolda.data.group.group_info.api.SubjectId

interface GroupCreateRepository {
    suspend fun createGroup(name: String, description: String, selectedSubject: SubjectId): GroupFullInfo
}
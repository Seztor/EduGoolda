package ru.itmo.edugoolda.data.group.create_group.internal

import ru.itmo.edugoolda.data.group.create_group.api.GroupCreateRepository
import ru.itmo.edugoolda.data.group.create_group.internal.dto.CreateGroupRequest
import ru.itmo.edugoolda.data.group.create_group.internal.dto.CreateSubjectRequest
import ru.itmo.edugoolda.data.group.group_info.api.GroupFullInfo
import ru.itmo.edugoolda.data.group.group_info.api.GroupSubject
import ru.itmo.edugoolda.data.group.group_info.api.SubjectId
import ru.itmo.edugoolda.data.group.group_info.internal.dto.toDomain

internal class GroupCreateRepositoryImpl(
    private val createGroupApi: GroupCreateApi
) : GroupCreateRepository {
    override suspend fun createGroup(name: String, description: String, selectedSubject: SubjectId): GroupFullInfo {
        val actionRequest = CreateGroupRequest(
            name = name,
            description = description,
            subjectId = selectedSubject.value
        )
        return createGroupApi.createGroup(actionRequest).toDomain()
    }

    override suspend fun createSubject(name: String): GroupSubject {
        val actionRequest = CreateSubjectRequest(
            name = name
        )
        return createGroupApi.createSubject(actionRequest).toDomain()
    }

    override suspend fun getSubjectIdByName(name: String): SubjectId {
        val subjectsList = createGroupApi.getSubjects().toDomain().subjects
        val foundSubject = subjectsList.firstOrNull { it.name == name }

        if (foundSubject != null) {
            return foundSubject.id
        } else {
            val createdSubject = createSubject(name)
            return createdSubject.id
        }
    }
}
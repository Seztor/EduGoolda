package ru.itmo.edugoolda.data.group.groupInvitationData.internal

import ru.itmo.edugoolda.data.group.groupInvitationData.api.GroupInvitationData
import ru.itmo.edugoolda.data.group.groupInvitationData.api.GroupInvitationDataRepository
import ru.itmo.edugoolda.data.group.groupInvitationData.internal.dto.toDomain
import ru.itmo.edugoolda.data.group.groupList.api.GroupId

internal class GroupInvitationDataRepositoryImpl(
    private val groupInvitationDataApi: GroupInvitationDataApi
) : GroupInvitationDataRepository {
    override suspend fun getGroupInvitationData(groupId: GroupId): GroupInvitationData {
        return groupInvitationDataApi.getGroupInvitationData(groupId.value).toDomain()
    }
}
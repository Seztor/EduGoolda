package ru.itmo.edugoolda.data.group.groupInvitationData.api

import ru.itmo.edugoolda.data.group.groupList.api.GroupId

interface GroupInvitationDataRepository {
    suspend fun getGroupInvitationData(groupId: GroupId): GroupInvitationData
}
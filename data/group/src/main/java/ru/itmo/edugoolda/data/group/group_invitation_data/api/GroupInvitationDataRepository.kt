package ru.itmo.edugoolda.data.group.group_invitation_data.api

import ru.itmo.edugoolda.data.group.group_list.api.GroupId

interface GroupInvitationDataRepository {
    suspend fun getGroupInvitationData(groupId: GroupId): GroupInvitationData
    suspend fun sendRequestJoinGroup(code: GroupInvitationCode) : GroupId
}
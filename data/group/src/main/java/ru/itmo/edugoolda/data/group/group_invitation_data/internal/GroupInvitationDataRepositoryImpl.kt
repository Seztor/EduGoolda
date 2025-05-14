package ru.itmo.edugoolda.data.group.group_invitation_data.internal

import ru.itmo.edugoolda.data.group.group_invitation_data.api.GroupInvitationCode
import ru.itmo.edugoolda.data.group.group_invitation_data.api.GroupInvitationData
import ru.itmo.edugoolda.data.group.group_invitation_data.api.GroupInvitationDataRepository
import ru.itmo.edugoolda.data.group.group_invitation_data.internal.dto.RequestJoinGroupDTO
import ru.itmo.edugoolda.data.group.group_invitation_data.internal.dto.toDomain
import ru.itmo.edugoolda.data.group.group_list.api.GroupId

internal class GroupInvitationDataRepositoryImpl(
    private val groupInvitationDataApi: GroupInvitationDataApi
) : GroupInvitationDataRepository {
    override suspend fun getGroupInvitationData(groupId: GroupId): GroupInvitationData {
        return groupInvitationDataApi.getGroupInvitationData(groupId.value).toDomain()
    }

    override suspend fun sendRequestJoinGroup(code: GroupInvitationCode) : GroupId {
        val actionRequest = RequestJoinGroupDTO(code = code.value)
        return groupInvitationDataApi.sendRequestJoinGroup(actionRequest).toDomain()
    }
}
package ru.itmo.edugoolda.data.group.group_list.api

import me.aartikov.replica.paged.PagedReplica

interface GroupListRepository {
    val groupInfoListReplica: PagedReplica<GroupInfoList>

    suspend fun deleteGroup(groupId: GroupId)
}
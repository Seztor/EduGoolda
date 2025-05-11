package ru.itmo.edugoolda.data.group.group_list.api

import me.aartikov.replica.keyed_paged.KeyedPagedReplica

interface GroupListRepository {
    val groupInfoListReplica: KeyedPagedReplica<String, GroupInfoList>

    suspend fun deleteGroup(groupId: GroupId)
}
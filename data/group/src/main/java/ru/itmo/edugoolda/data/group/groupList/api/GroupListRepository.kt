package ru.itmo.edugoolda.data.group.groupList.api

import me.aartikov.replica.paged.PagedReplica

interface GroupListRepository {
    val groupListReplica: PagedReplica<GroupList>
}
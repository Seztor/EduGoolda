package ru.itmo.edugoolda.data.group.groupList.api

import me.aartikov.replica.paged.PagedReplica

interface GroupRepository {
    val groupListReplica: PagedReplica<GroupList>
}
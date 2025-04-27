package ru.itmo.edugoolda.data.group.studentGroups.api

import me.aartikov.replica.paged.PagedReplica

interface StudentGroupRepository {
    val studentGroupReplica: PagedReplica<StudentGroups>

    suspend fun onGroupRequest(id: String) {}
}
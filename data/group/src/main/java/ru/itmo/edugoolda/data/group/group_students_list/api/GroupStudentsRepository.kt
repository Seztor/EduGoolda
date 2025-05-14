package ru.itmo.edugoolda.data.group.group_students_list.api

import me.aartikov.replica.keyed_paged.KeyedPagedReplica
import ru.itmo.edugoolda.data.group.group_list.api.GroupId
import ru.itmo.edugoolda.data.user.api.UserId

interface GroupStudentsRepository {
    val groupOfStudentsReplica: KeyedPagedReplica<GroupId, GroupStudentsList>

    suspend fun kickStudentFromGroup(action: KickType, groupId: GroupId, studentId: UserId)
    suspend fun leaveFromGroup(groupId: GroupId)
}
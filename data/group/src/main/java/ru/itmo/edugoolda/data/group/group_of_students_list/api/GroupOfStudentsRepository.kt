package ru.itmo.edugoolda.data.group.group_of_students_list.api

import me.aartikov.replica.keyed_paged.KeyedPagedReplica
import ru.itmo.edugoolda.data.group.group_list.api.GroupId
import ru.itmo.edugoolda.data.user.api.UserId

interface GroupOfStudentsRepository {
    val groupOfStudentsReplica: KeyedPagedReplica<GroupId, GroupOfStudentsList>

    suspend fun kickStudentFromGroup(action: KickType, groupId: GroupId, studentId: UserId)
}
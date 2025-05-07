package ru.itmo.edugoolda.data.group.groupOfStudentsList.api

import me.aartikov.replica.keyed_paged.KeyedPagedReplica
import ru.itmo.edugoolda.data.group.groupList.api.GroupId

interface GroupOfStudentsRepository {
    val groupOfStudentsReplica: KeyedPagedReplica<GroupId, GroupOfStudentsList>
}
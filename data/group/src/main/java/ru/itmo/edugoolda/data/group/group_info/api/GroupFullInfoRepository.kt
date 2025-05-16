package ru.itmo.edugoolda.data.group.group_info.api

import me.aartikov.replica.keyed.KeyedReplica
import ru.itmo.edugoolda.data.group.group_list.api.GroupId

interface GroupFullInfoRepository {
    val groupInfoReplica: KeyedReplica<GroupId, GroupFullInfo>
}
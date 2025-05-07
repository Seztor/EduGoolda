package ru.itmo.edugoolda.data.group.groupInfo.api

import me.aartikov.replica.keyed.KeyedReplica
import ru.itmo.edugoolda.data.group.groupList.api.GroupId

interface GroupFullInfoRepository {
    val groupInfoReplica: KeyedReplica<GroupId, GroupFullInfo>
}
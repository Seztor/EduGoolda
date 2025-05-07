package ru.itmo.edugoolda.data.group.groupInfo.internal

import me.aartikov.replica.client.ReplicaClient
import me.aartikov.replica.keyed.KeyedReplicaSettings
import me.aartikov.replica.single.ReplicaSettings
import ru.itmo.edugoolda.data.group.groupList.api.GroupId
import ru.itmo.edugoolda.data.group.groupInfo.api.GroupFullInfoRepository
import ru.itmo.edugoolda.data.group.groupInfo.internal.dto.toDomain
import kotlin.time.Duration.Companion.minutes

internal class GroupFullInfoRepositoryImpl(
    replicaClient: ReplicaClient,
    groupFullInfoApi: GroupFullInfoApi
) : GroupFullInfoRepository {
    override val groupInfoReplica = replicaClient.createKeyedReplica(
        name = "full group info replica",
        childName = {
            "child $it"
        },
        settings = KeyedReplicaSettings(maxCount = 10),
        childSettings = {
            ReplicaSettings(staleTime = 5.minutes)
        },
        fetcher = { key: GroupId ->
            groupFullInfoApi.getGroupFullInfo(key.value).toDomain()
        }
    )
}
package ru.itmo.edugoolda.data.user.internal

import me.aartikov.replica.client.ReplicaClient
import me.aartikov.replica.keyed.KeyedReplicaSettings
import me.aartikov.replica.single.ReplicaSettings
import ru.itmo.edugoolda.data.user.api.UserId
import ru.itmo.edugoolda.data.user.api.UserRepository
import ru.itmo.edugoolda.data.user.internal.dto.toDomain
import kotlin.time.Duration.Companion.minutes

class UserRepositoryImpl(
    replicaClient: ReplicaClient,
    userApi: UserApi
) : UserRepository {

    override val profileReplica = replicaClient.createKeyedReplica(
        name = "profile replica",
        childName = {
            "child $it"
        },
        settings = KeyedReplicaSettings(maxCount = 10),
        childSettings = {
            ReplicaSettings(staleTime = 5.minutes)
        },
        fetcher = { key: UserId ->
            userApi.getUserProfile(key.value).toDomain()
        }

    )
}
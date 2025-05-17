package ru.itmo.edugoolda.data.user.internal

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import me.aartikov.replica.client.ReplicaClient
import me.aartikov.replica.keyed.KeyedReplicaSettings
import me.aartikov.replica.single.ReplicaSettings
import ru.itmo.edugoolda.data.user.api.UserId
import ru.itmo.edugoolda.data.user.api.UserInfoStore
import ru.itmo.edugoolda.data.user.api.UserRepository
import ru.itmo.edugoolda.data.user.internal.dto.toDomain
import kotlin.time.Duration.Companion.minutes

class UserRepositoryImpl(
    replicaClient: ReplicaClient,
    userApi: UserApi,
    userInfoStore: UserInfoStore
) : UserRepository {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val currentUserId = userInfoStore.getUserId()
        .stateIn(coroutineScope, SharingStarted.Eagerly, null)

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

    override val currentProfileReplica = replicaClient.createReplica(
        name = "current profile",
        settings = ReplicaSettings(staleTime = 5.minutes),
        fetcher = {
            val id = currentUserId.value ?: error("Current user id is null")

            userApi.getUserProfile(id.value).toDomain()
        }
    )
}
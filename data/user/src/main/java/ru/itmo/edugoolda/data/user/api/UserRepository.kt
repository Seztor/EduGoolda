package ru.itmo.edugoolda.data.user.api

import me.aartikov.replica.keyed.KeyedReplica
import me.aartikov.replica.single.Replica

interface UserRepository {
    val profileReplica: KeyedReplica<UserId, Profile>
    val currentProfileReplica: Replica<Profile>
}
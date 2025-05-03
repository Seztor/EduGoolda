package ru.itmo.edugoolda.data.user.api

import me.aartikov.replica.keyed.KeyedReplica

interface UserRepository {
    val profileReplica: KeyedReplica<UserId, Profile>

}
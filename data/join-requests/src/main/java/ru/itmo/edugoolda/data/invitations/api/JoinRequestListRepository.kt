package ru.itmo.edugoolda.data.invitations.api

import me.aartikov.replica.paged.PagedReplica

interface JoinRequestListRepository {
    val joinRequestListReplica : PagedReplica<JoinRequestList>
}
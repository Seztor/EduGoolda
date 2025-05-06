package ru.itmo.edugoolda.data.invitations.api

import me.aartikov.replica.paged.PagedReplica

interface InvitationListRepository {
    val invitationListReplica : PagedReplica<InvitationList>
}
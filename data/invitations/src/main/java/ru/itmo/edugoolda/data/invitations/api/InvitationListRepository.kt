package ru.itmo.edugoolda.data.invitations.api

import me.aartikov.replica.paged.PagedReplica
import ru.itmo.edugoolda.core.utils.PageWithTotalAmount

interface InvitationListRepository {
    val invitationListReplica : PagedReplica<InvitationList>
}
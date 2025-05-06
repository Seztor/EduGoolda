package ru.itmo.edugoolda.data.invitations.internal

import me.aartikov.replica.algebra.paged.map
import me.aartikov.replica.client.ReplicaClient
import me.aartikov.replica.paged.PagedFetcher
import me.aartikov.replica.paged.PagedReplicaSettings
import ru.itmo.edugoolda.core.utils.PageWithTotalAmount
import ru.itmo.edugoolda.data.invitations.api.Invitation
import ru.itmo.edugoolda.data.invitations.api.InvitationList
import me.aartikov.replica.paged.PagedData
import ru.itmo.edugoolda.data.invitations.api.InvitationListRepository
import ru.itmo.edugoolda.data.invitations.internal.dto.toDomain
import kotlin.time.Duration.Companion.minutes

class InvitationListRepositoryImpl(
    replicaClient: ReplicaClient,
    private val api: InvitationsApi

) : InvitationListRepository {
    companion object {
        private const val PAGE_SIZE = 20
    }

    override val invitationListReplica = replicaClient.createPagedReplica(
        name = "invitation list replica",
        settings = PagedReplicaSettings(staleTime = 5.minutes),
        idExtractor = { it.id },
        fetcher = object : PagedFetcher<Invitation, PageWithTotalAmount<Invitation>> {

            override suspend fun fetchFirstPage(): PageWithTotalAmount<Invitation> {
                val invitationList = api.getInvitationList(pageSize = PAGE_SIZE, 1).toDomain()

                return PageWithTotalAmount(
                    hasNextPage = invitationList.total > PAGE_SIZE,
                    hasPreviousPage = false,
                    items = invitationList.invitationList,
                    total = invitationList.total

                )
            }

            override suspend fun fetchNextPage(currentData: PagedData<Invitation, PageWithTotalAmount<Invitation>>): PageWithTotalAmount<Invitation> {
                val invitationList =
                    api.getInvitationList(pageSize = PAGE_SIZE, currentData.pages.size + 1)
                        .toDomain()

                return PageWithTotalAmount(
                    hasNextPage = invitationList.total > PAGE_SIZE * (currentData.pages.size + 1),
                    hasPreviousPage = false,
                    items = invitationList.invitationList,
                    total = invitationList.total

                )
            }
        }
    ).map {
        InvitationList(
            invitationList = it.items,
            hasNextPage = it.hasNextPage
        )
    }
}
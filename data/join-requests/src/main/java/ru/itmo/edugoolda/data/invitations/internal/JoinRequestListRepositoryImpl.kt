package ru.itmo.edugoolda.data.invitations.internal

import me.aartikov.replica.algebra.paged.map
import me.aartikov.replica.client.ReplicaClient
import me.aartikov.replica.paged.PagedFetcher
import me.aartikov.replica.paged.PagedReplicaSettings
import ru.itmo.edugoolda.core.utils.PageWithTotalAmount
import ru.itmo.edugoolda.data.invitations.api.JoinRequest
import ru.itmo.edugoolda.data.invitations.api.JoinRequestList
import me.aartikov.replica.paged.PagedData
import ru.itmo.edugoolda.data.invitations.api.JoinRequestListRepository
import ru.itmo.edugoolda.data.invitations.internal.dto.toDomain
import kotlin.time.Duration.Companion.minutes

class JoinRequestListRepositoryImpl(
    replicaClient: ReplicaClient,
    private val api: JoinRequestApi

) : JoinRequestListRepository {
    companion object {
        private const val PAGE_SIZE = 20
    }

    override val joinRequestListReplica = replicaClient.createPagedReplica(
        name = "invitation list replica",
        settings = PagedReplicaSettings(staleTime = 5.minutes),
        idExtractor = { it.id },
        fetcher = object : PagedFetcher<JoinRequest, PageWithTotalAmount<JoinRequest>> {

            override suspend fun fetchFirstPage(): PageWithTotalAmount<JoinRequest> {
                val invitationList = api.getInvitationList(pageSize = PAGE_SIZE, 1).toDomain()

                return PageWithTotalAmount(
                    hasNextPage = invitationList.total > PAGE_SIZE,
                    hasPreviousPage = false,
                    items = invitationList.joinRequestList,
                    total = invitationList.total

                )
            }

            override suspend fun fetchNextPage(currentData: PagedData<JoinRequest, PageWithTotalAmount<JoinRequest>>): PageWithTotalAmount<JoinRequest> {
                val invitationList =
                    api.getInvitationList(pageSize = PAGE_SIZE, currentData.pages.size + 1)
                        .toDomain()

                return PageWithTotalAmount(
                    hasNextPage = invitationList.total > PAGE_SIZE * (currentData.pages.size + 1),
                    hasPreviousPage = false,
                    items = invitationList.joinRequestList,
                    total = invitationList.total

                )
            }
        }
    ).map {
        JoinRequestList(
            joinRequestList = it.items,
            hasNextPage = it.hasNextPage
        )
    }
}
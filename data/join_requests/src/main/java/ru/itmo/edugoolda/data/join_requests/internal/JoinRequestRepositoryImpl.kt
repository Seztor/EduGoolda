package ru.itmo.edugoolda.data.join_requests.internal

import me.aartikov.replica.algebra.paged.map
import me.aartikov.replica.client.ReplicaClient
import me.aartikov.replica.paged.PagedFetcher
import me.aartikov.replica.paged.PagedReplicaSettings
import ru.itmo.edugoolda.core.utils.PageWithTotalAmount
import ru.itmo.edugoolda.data.join_requests.api.JoinRequest
import ru.itmo.edugoolda.data.join_requests.api.JoinRequestList
import me.aartikov.replica.paged.PagedData
import ru.itmo.edugoolda.data.join_requests.api.JoinRequestAction
import ru.itmo.edugoolda.data.join_requests.api.JoinRequestId
import ru.itmo.edugoolda.data.join_requests.api.JoinRequestRepository
import ru.itmo.edugoolda.data.join_requests.internal.dto.JoinRequestResponseRequest
import ru.itmo.edugoolda.data.join_requests.internal.dto.toDomain
import kotlin.time.Duration.Companion.minutes

class JoinRequestRepositoryImpl(
    replicaClient: ReplicaClient,
    private val api: JoinRequestApi

) : JoinRequestRepository {
    companion object {
        private const val PAGE_SIZE = 20
    }

    override suspend fun respondToJoinRequest(
        joinRequestId: JoinRequestId,
        action: JoinRequestAction
    ) {
        val joinRequestResponseRequest = JoinRequestResponseRequest(
            action = when (action) {
                JoinRequestAction.Accept -> "accept"
                JoinRequestAction.Decline -> "decline"
                JoinRequestAction.DeclineAndBan -> "decline_and_ban"
            }
        )
        api.respondToJoinRequest(joinRequestId.value, joinRequestResponseRequest)
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
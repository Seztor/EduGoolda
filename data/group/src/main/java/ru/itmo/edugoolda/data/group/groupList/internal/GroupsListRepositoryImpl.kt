package ru.itmo.edugoolda.data.group.groupList.internal

import me.aartikov.replica.algebra.paged.map
import me.aartikov.replica.client.ReplicaClient
import me.aartikov.replica.paged.PagedData
import me.aartikov.replica.paged.PagedFetcher
import me.aartikov.replica.paged.PagedReplicaSettings
import ru.itmo.edugoolda.core.utils.PageWithTotalAmount
import ru.itmo.edugoolda.data.group.groupInfo.api.GroupInfo
import ru.itmo.edugoolda.data.group.groupList.api.GroupListRepository
import ru.itmo.edugoolda.data.group.groupList.api.GroupList
import ru.itmo.edugoolda.data.group.groupInfo.internal.dto.toDomain
import ru.itmo.edugoolda.data.group.groupList.internal.dto.toDomain
import kotlin.time.Duration.Companion.minutes

internal class GroupsListRepositoryImpl(
    replicaClient: ReplicaClient,
    groupListApi: GroupListApi,
) : GroupListRepository {
    companion object {
        private const val PAGE_SIZE = 20
    }

    override val groupListReplica = replicaClient.createPagedReplica(
        name = "group list replica",
        settings = PagedReplicaSettings(staleTime = 5.minutes),
        idExtractor = { it.id },
        fetcher = object : PagedFetcher<GroupInfo, PageWithTotalAmount<GroupInfo>> {
            override suspend fun fetchFirstPage(): PageWithTotalAmount<GroupInfo> {
                val groups =
                    groupListApi.getGroupsList(
                        query = null,
                        subjectId = null,
                        pageSize = PAGE_SIZE,
                        page = 1
                    ).toDomain()

                return PageWithTotalAmount(
                    hasNextPage = groups.total > PAGE_SIZE,
                    hasPreviousPage = false,
                    items = groups.groups,
                    total = groups.total
                )
            }

            override suspend fun fetchNextPage(currentData: PagedData<GroupInfo, PageWithTotalAmount<GroupInfo>>): PageWithTotalAmount<GroupInfo> {
                val groups = groupListApi.getGroupsList(
                    query = null,
                    subjectId = null,
                    pageSize = PAGE_SIZE,
                    page = currentData.pages.size + 1
                ).toDomain()

                return PageWithTotalAmount(
                    hasNextPage = groups.total > PAGE_SIZE * (currentData.pages.size + 1),
                    hasPreviousPage = false,
                    items = groups.groups,
                    total = groups.total
                )
            }
        }
    ).map { GroupList(it.items, it.hasNextPage) }
}

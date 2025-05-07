package ru.itmo.edugoolda.data.group.groupOfStudentsList.internal

import me.aartikov.replica.algebra.paged.map
import me.aartikov.replica.client.ReplicaClient
import me.aartikov.replica.keyed_paged.KeyedPagedFetcher
import me.aartikov.replica.keyed_paged.KeyedPagedReplica
import me.aartikov.replica.keyed_paged.KeyedPagedReplicaSettings
import me.aartikov.replica.paged.PagedData
import me.aartikov.replica.paged.PagedReplicaSettings
import ru.itmo.edugoolda.core.utils.PageWithTotalAmount
import ru.itmo.edugoolda.data.group.groupList.api.GroupId
import ru.itmo.edugoolda.data.group.groupOfStudentsList.api.GroupOfStudentsList
import ru.itmo.edugoolda.data.group.groupOfStudentsList.api.GroupOfStudentsRepository
import ru.itmo.edugoolda.data.group.groupOfStudentsList.internal.dto.toDomain
import ru.itmo.edugoolda.data.user.api.UserInfo
import kotlin.time.Duration.Companion.minutes

internal class GroupOfStudentsRepositoryImpl(
    replicaClient: ReplicaClient,
    groupOfStudentsApi: GroupOfStudentsApi,
) : GroupOfStudentsRepository {
    companion object {
        private const val PAGE_SIZE = 20
    }

    override val groupOfStudentsReplica: KeyedPagedReplica<GroupId, GroupOfStudentsList> = replicaClient.createKeyedPagedReplica(
        name = "students list replica",
        childName = {
            "child $it"
        },
        settings = KeyedPagedReplicaSettings(maxCount = 10),
        idExtractor = { it.id },
        childSettings = {
            PagedReplicaSettings(staleTime = 5.minutes)
        },
        fetcher = object : KeyedPagedFetcher<GroupId, UserInfo, PageWithTotalAmount<UserInfo>> {
            override suspend fun fetchFirstPage(key: GroupId): PageWithTotalAmount<UserInfo> {
                val users = groupOfStudentsApi.getStudentsList(
                    id = key.value,
                    pageSize = PAGE_SIZE,
                    page = 1,
                ).toDomain()

                return PageWithTotalAmount(
                    hasNextPage = users.total > PAGE_SIZE,
                    hasPreviousPage = false,
                    items = users.users,
                    total = users.total
                )
            }

            override suspend fun fetchNextPage(
                key: GroupId,
                currentData: PagedData<UserInfo, PageWithTotalAmount<UserInfo>>,
            ): PageWithTotalAmount<UserInfo> {
                val users = groupOfStudentsApi.getStudentsList(
                    id = key.value,
                    pageSize = PAGE_SIZE,
                    page = currentData.pages.size + 1
                ).toDomain()

                return PageWithTotalAmount(
                    hasNextPage = users.total > PAGE_SIZE * (currentData.pages.size + 1),
                    hasPreviousPage = false,
                    items = users.users,
                    total = users.total
                )
            }
        }
    ).map { _, data ->
        GroupOfStudentsList(data.items, data.hasNextPage)
    }
}
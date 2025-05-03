package ru.itmo.edugoolda.data.group.studentGroups.internal

import me.aartikov.replica.algebra.paged.map
import me.aartikov.replica.client.ReplicaClient
import me.aartikov.replica.paged.PagedData
import me.aartikov.replica.paged.PagedFetcher
import me.aartikov.replica.paged.PagedReplicaSettings
import ru.itmo.edugoolda.core.utils.PageWithTotalAmount
import ru.itmo.edugoolda.data.group.studentGroups.api.StudentGroupInfo
import ru.itmo.edugoolda.data.group.studentGroups.api.StudentGroupRepository
import ru.itmo.edugoolda.data.group.studentGroups.api.StudentGroups
import ru.itmo.edugoolda.data.group.studentGroups.internal.dto.toDomain
import kotlin.time.Duration.Companion.minutes

internal class StudentGroupsRepositoryImpl(
    replicaClient: ReplicaClient,
    studentGroupsApi: StudentGroupsApi,
) : StudentGroupRepository {
    companion object {
        private const val PAGE_SIZE = 20
    }

    override val studentGroupReplica = replicaClient.createPagedReplica(
        name = "student group replica",
        settings = PagedReplicaSettings(staleTime = 5.minutes),
        idExtractor = { it.id },
        fetcher = object : PagedFetcher<StudentGroupInfo, PageWithTotalAmount<StudentGroupInfo>> {
            override suspend fun fetchFirstPage(): PageWithTotalAmount<StudentGroupInfo> {
                val studentGroups =
                    studentGroupsApi.getGroupsList(
                        query = null,
                        subjectId = null,
                        pageSize = PAGE_SIZE,
                        page = 1
                    ).toDomain()

                return PageWithTotalAmount(
                    hasNextPage = studentGroups.total > PAGE_SIZE,
                    hasPreviousPage = false,
                    items = studentGroups.groups,
                    total = studentGroups.total
                )
            }

            override suspend fun fetchNextPage(currentData: PagedData<StudentGroupInfo, PageWithTotalAmount<StudentGroupInfo>>): PageWithTotalAmount<StudentGroupInfo> {
                val studentGroups = studentGroupsApi.getGroupsList(
                    query = null,
                    subjectId = null,
                    pageSize = PAGE_SIZE,
                    page = currentData.pages.size + 1
                ).toDomain()

                return PageWithTotalAmount(
                    hasNextPage = studentGroups.total > PAGE_SIZE * (currentData.pages.size + 1),
                    hasPreviousPage = false,
                    items = studentGroups.groups,
                    total = studentGroups.total
                )
            }
        }
    ).map { StudentGroups(it.items, it.hasNextPage) }
}

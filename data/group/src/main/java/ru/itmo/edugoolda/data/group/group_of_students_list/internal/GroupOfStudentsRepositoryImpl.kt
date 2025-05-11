package ru.itmo.edugoolda.data.group.group_of_students_list.internal

import me.aartikov.replica.algebra.paged.map
import me.aartikov.replica.client.ReplicaClient
import me.aartikov.replica.keyed_paged.KeyedPagedFetcher
import me.aartikov.replica.keyed_paged.KeyedPagedReplica
import me.aartikov.replica.keyed_paged.KeyedPagedReplicaSettings
import me.aartikov.replica.paged.PagedData
import me.aartikov.replica.paged.PagedReplicaSettings
import ru.itmo.edugoolda.core.utils.PageWithTotalAmount
import ru.itmo.edugoolda.data.group.group_list.api.GroupId
import ru.itmo.edugoolda.data.group.group_of_students_list.api.GroupOfStudentsList
import ru.itmo.edugoolda.data.group.group_of_students_list.api.GroupOfStudentsRepository
import ru.itmo.edugoolda.data.group.group_of_students_list.api.KickType
import ru.itmo.edugoolda.data.group.group_of_students_list.internal.dto.KickStudentRequest
import ru.itmo.edugoolda.data.group.group_of_students_list.internal.dto.toDomain
import ru.itmo.edugoolda.data.user.api.UserId
import ru.itmo.edugoolda.data.user.api.UserInfo
import kotlin.time.Duration.Companion.minutes

internal class GroupOfStudentsRepositoryImpl(
    replicaClient: ReplicaClient,
    private val groupOfStudentsApi: GroupOfStudentsApi,
) : GroupOfStudentsRepository {
    companion object {
        private const val PAGE_SIZE = 20
    }

    private val _groupOfStudentsReplica = replicaClient.createKeyedPagedReplica(
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
    )

    override val groupOfStudentsReplica = _groupOfStudentsReplica.map { _, data ->
        GroupOfStudentsList(data.items, data.hasNextPage)
    }

    override suspend fun kickStudentFromGroup(
        action: KickType,
        groupId: GroupId,
        studentId: UserId,
    ) {
        val actionType = when (action) {
            KickType.Kick -> "kick"
            KickType.KickAndBan -> "kick_and_ban"
        }
        val kickActionRequest = KickStudentRequest(
            actionType,
            groupId.value,
            studentId.value
        )
        groupOfStudentsApi.kickStudentFromGroup(kickActionRequest)
        _groupOfStudentsReplica.mutateData(
            key = groupId,
            transform = { pages ->
                pages.map { page ->
                    page.copy(
                        hasNextPage = page.hasNextPage,
                        hasPreviousPage = page.hasPreviousPage,
                        items = page.items.filter { it.id != studentId },
                        total = page.total
                    )
                }
            }
        )
    }
}
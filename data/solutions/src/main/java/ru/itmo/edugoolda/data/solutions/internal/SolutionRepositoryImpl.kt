package ru.itmo.edugoolda.data.solutions.internal

import me.aartikov.replica.algebra.paged.map
import me.aartikov.replica.client.ReplicaClient
import me.aartikov.replica.paged.PagedFetcher
import me.aartikov.replica.paged.PagedReplicaSettings
import ru.itmo.edugoolda.core.utils.PageWithTotalAmount
import ru.itmo.edugoolda.data.solutions.api.SolutionList
import me.aartikov.replica.paged.PagedData
import ru.itmo.edugoolda.data.solutions.api.SolutionRepository
import ru.itmo.edugoolda.data.solutions.internal.dto.toDomain
import ru.itmo.edugoolda.data.solutions.api.Solution
import kotlin.time.Duration.Companion.minutes

class SolutionRepositoryImpl(
    replicaClient: ReplicaClient,
    private val api: SolutionApi

) : SolutionRepository {
    companion object {
        private const val PAGE_SIZE = 20
    }
    override val solutionListReplica = replicaClient.createPagedReplica(
        name = "invitation list replica",
        settings = PagedReplicaSettings(staleTime = 5.minutes),
        idExtractor = { it.id },
        fetcher = object : PagedFetcher<Solution, PageWithTotalAmount<Solution>> {

            override suspend fun fetchFirstPage(): PageWithTotalAmount<Solution> {
                val solutionList = api.getSolutionList(pageSize = PAGE_SIZE, 1).toDomain()

                return PageWithTotalAmount(
                    hasNextPage = solutionList.total > PAGE_SIZE,
                    hasPreviousPage = false,
                    items = solutionList.solutionList,
                    total = solutionList.total

                )
            }

            override suspend fun fetchNextPage(currentData: PagedData<Solution, PageWithTotalAmount<Solution>>): PageWithTotalAmount<Solution> {
                val solutionList =
                    api.getSolutionList(pageSize = PAGE_SIZE, currentData.pages.size + 1)
                        .toDomain()

                return PageWithTotalAmount(
                    hasNextPage = solutionList.total > PAGE_SIZE * (currentData.pages.size + 1),
                    hasPreviousPage = false,
                    items = solutionList.solutionList,
                    total = solutionList.total

                )
            }
        }
    ).map {
        SolutionList(
            solutionList = it.items,
            hasNextPage = it.hasNextPage
        )
    }
}
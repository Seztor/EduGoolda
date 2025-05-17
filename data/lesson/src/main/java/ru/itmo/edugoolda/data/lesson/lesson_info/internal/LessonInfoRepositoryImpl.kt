package ru.itmo.edugoolda.data.lesson.lesson_info.internal

import me.aartikov.replica.algebra.paged.map
import me.aartikov.replica.client.ReplicaClient
import me.aartikov.replica.paged.PagedFetcher
import me.aartikov.replica.paged.PagedReplicaSettings
import ru.itmo.edugoolda.core.utils.PageWithTotalAmount
import ru.itmo.edugoolda.data.lesson.lesson_info.api.LessonInfoList
import me.aartikov.replica.paged.PagedData
import ru.itmo.edugoolda.data.lesson.lesson_info.api.LessonInfoRepository
import ru.itmo.edugoolda.data.lesson.lesson_info.internal.dto.toDomain
import ru.itmo.edugoolda.data.lesson.lesson_info.api.LessonInfo
import kotlin.time.Duration.Companion.minutes

class LessonInfoRepositoryImpl(
    replicaClient: ReplicaClient,
    private val api: LessonInfoApi

) : LessonInfoRepository {
    companion object {
        private const val PAGE_SIZE = 20
    }

    override suspend fun deleteLesson(lessonInfo: LessonInfo) {
        api.deleteLesson(lessonInfo.id.value)
    }

    override val lessonInfoListReplica = replicaClient.createPagedReplica(
        name = "invitation list replica",
        settings = PagedReplicaSettings(staleTime = 5.minutes),
        idExtractor = { it.id },
        fetcher = object : PagedFetcher<LessonInfo, PageWithTotalAmount<LessonInfo>> {

            override suspend fun fetchFirstPage(): PageWithTotalAmount<LessonInfo> {
                val lessonInfoList = api.getLessonInfoList(pageSize = PAGE_SIZE, 1).toDomain()

                return PageWithTotalAmount(
                    hasNextPage = lessonInfoList.total > PAGE_SIZE,
                    hasPreviousPage = false,
                    items = lessonInfoList.lessonInfoList,
                    total = lessonInfoList.total

                )
            }

            override suspend fun fetchNextPage(currentData: PagedData<LessonInfo, PageWithTotalAmount<LessonInfo>>): PageWithTotalAmount<LessonInfo> {
                val lessonInfoList =
                    api.getLessonInfoList(pageSize = PAGE_SIZE, currentData.pages.size + 1)
                        .toDomain()

                return PageWithTotalAmount(
                    hasNextPage = lessonInfoList.total > PAGE_SIZE * (currentData.pages.size + 1),
                    hasPreviousPage = false,
                    items = lessonInfoList.lessonInfoList,
                    total = lessonInfoList.total

                )
            }
        }
    ).map {
        LessonInfoList(
            lessonInfoList = it.items,
            hasNextPage = it.hasNextPage
        )
    }
}
package ru.itmo.edugoolda.data.solutions.internal

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Query
import ru.itmo.edugoolda.data.solutions.internal.dto.SolutionInfoListResponse

interface SolutionApi {

    @GET("/api/v1/solutions")
    suspend fun getSolutionList(
        @Query("page_size") pageSize: Int,
        @Query("page") page: Int,
        @Query("group_id") groupId: String? = null,
        @Query("lesson_id") lessonId: String? = null,
        @Query("status") status: String? = null,
    ): SolutionInfoListResponse
}

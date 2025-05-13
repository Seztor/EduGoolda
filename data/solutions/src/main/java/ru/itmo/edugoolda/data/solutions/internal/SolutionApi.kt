package ru.itmo.edugoolda.data.solutions.internal

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Query
import ru.itmo.edugoolda.data.solutions.internal.dto.SolutionListResponse

interface SolutionApi {


    @GET("/api/v1/solutions") //TODO: Change url
    suspend fun getSolutionList(
        @Query("page_size") pageSize: Int,
        @Query("page") page: Int
    ): SolutionListResponse
}

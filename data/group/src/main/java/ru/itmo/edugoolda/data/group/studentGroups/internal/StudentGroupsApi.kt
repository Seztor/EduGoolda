package ru.itmo.edugoolda.data.group.studentGroups.internal

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Query
import ru.itmo.edugoolda.data.group.studentGroups.internal.dto.StudentGroupsResponse

interface StudentGroupsApi {
    @GET("/api/v1/groups")
    suspend fun getGroupsList(
        @Query("page_size") pageSize: Int,
        @Query("page") page: Int
    ): StudentGroupsResponse
}
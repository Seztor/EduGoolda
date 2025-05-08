package ru.itmo.edugoolda.data.group.group_list.internal

import de.jensklingenberg.ktorfit.http.DELETE
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path
import de.jensklingenberg.ktorfit.http.Query
import ru.itmo.edugoolda.data.group.group_list.internal.dto.GroupListResponse

internal interface GroupListApi {
    @GET("/api/v1/groups")
    suspend fun getGroupsList(
        @Query("query") query: String?,
        @Query("subject_id") subjectId: String?,
        @Query("page_size") pageSize: Int,
        @Query("page") page: Int
    ): GroupListResponse

    @DELETE("/api/v1/group/{groupId}")
    suspend fun deleteGroup(@Path("groupId") groupId: String)
}

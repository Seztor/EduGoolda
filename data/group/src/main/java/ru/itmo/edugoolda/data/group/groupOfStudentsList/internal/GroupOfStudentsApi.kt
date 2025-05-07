package ru.itmo.edugoolda.data.group.groupOfStudentsList.internal

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path
import de.jensklingenberg.ktorfit.http.Query
import ru.itmo.edugoolda.data.group.groupOfStudentsList.internal.dto.GroupOfStudentsResponse

internal interface GroupOfStudentsApi {
    @GET("/api/v1/group/{groupId}/students")
    suspend fun getStudentsList(
        @Path("groupId") id: String,
        @Query("page_size") pageSize: Int,
        @Query("page") page: Int
    ): GroupOfStudentsResponse
}
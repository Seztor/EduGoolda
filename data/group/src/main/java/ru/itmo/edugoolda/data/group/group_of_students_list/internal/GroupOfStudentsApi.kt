package ru.itmo.edugoolda.data.group.group_of_students_list.internal

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.Path
import de.jensklingenberg.ktorfit.http.Query
import ru.itmo.edugoolda.data.group.group_of_students_list.internal.dto.GroupOfStudentsResponse
import ru.itmo.edugoolda.data.group.group_of_students_list.internal.dto.KickStudentRequest

internal interface GroupOfStudentsApi {
    @GET("/api/v1/group/{groupId}/students")
    suspend fun getStudentsList(
        @Path("groupId") id: String,
        @Query("page_size") pageSize: Int,
        @Query("page") page: Int
    ): GroupOfStudentsResponse

    @POST("/api/v1/group/kick")
    suspend fun kickStudentFromGroup(@Body request: KickStudentRequest)
}
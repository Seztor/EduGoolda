package ru.itmo.edugoolda.data.group.create_group.internal

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST
import ru.itmo.edugoolda.data.group.create_group.internal.dto.CreateGroupRequest
import ru.itmo.edugoolda.data.group.create_group.internal.dto.CreateSubjectRequest
import ru.itmo.edugoolda.data.group.group_info.internal.dto.GroupFullInfoDTO
import ru.itmo.edugoolda.data.group.group_info.internal.dto.GroupSubjectDTO
import ru.itmo.edugoolda.data.group.group_info.internal.dto.GroupSubjectsListDTO

internal interface GroupCreateApi {
    @POST("api/v1/group/")
    suspend fun createGroup(@Body request: CreateGroupRequest): GroupFullInfoDTO

    @POST("/api/v1/subject")
    suspend fun createSubject(@Body request: CreateSubjectRequest) : GroupSubjectDTO

    @GET("/api/v1/subjects")
    suspend fun getSubjects() : GroupSubjectsListDTO
}
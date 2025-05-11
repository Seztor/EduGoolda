package ru.itmo.edugoolda.data.group.create_group.internal

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.POST
import ru.itmo.edugoolda.data.group.create_group.internal.dto.CreateGroupRequest
import ru.itmo.edugoolda.data.group.group_info.internal.dto.GroupFullInfoDTO

internal interface GroupCreateApi {
    @POST("api/v1/group/")
    suspend fun createGroup(@Body request: CreateGroupRequest): GroupFullInfoDTO
}
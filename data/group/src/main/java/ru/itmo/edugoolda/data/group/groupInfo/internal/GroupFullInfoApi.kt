package ru.itmo.edugoolda.data.group.groupInfo.internal

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path
import ru.itmo.edugoolda.data.group.groupInfo.internal.dto.GroupFullInfoDTO

internal interface GroupFullInfoApi {
    @GET("/api/v1/subjects/{groupId}")
    suspend fun getGroupFullInfo(@Path("groupId") id: String): GroupFullInfoDTO
}

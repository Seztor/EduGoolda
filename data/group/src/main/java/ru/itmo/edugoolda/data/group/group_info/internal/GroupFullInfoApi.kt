package ru.itmo.edugoolda.data.group.group_info.internal

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path
import ru.itmo.edugoolda.data.group.group_info.internal.dto.GroupFullInfoDTO

internal interface GroupFullInfoApi {
    @GET("api/v1/group/{groupId}")
    suspend fun getGroupFullInfo(@Path("groupId") id: String): GroupFullInfoDTO
}

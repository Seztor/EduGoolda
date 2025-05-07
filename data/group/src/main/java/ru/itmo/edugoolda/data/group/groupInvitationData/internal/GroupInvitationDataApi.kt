package ru.itmo.edugoolda.data.group.groupInvitationData.internal

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path
import ru.itmo.edugoolda.data.group.groupInvitationData.internal.dto.GroupInvitationDataDTO

internal interface GroupInvitationDataApi {
    @GET("/api/v1/group_invitation/{groupId}")
    suspend fun getGroupInvitationData(@Path("groupId") id: String): GroupInvitationDataDTO
}
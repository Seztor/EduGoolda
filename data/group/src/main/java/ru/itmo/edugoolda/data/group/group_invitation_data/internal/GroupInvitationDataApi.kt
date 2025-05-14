package ru.itmo.edugoolda.data.group.group_invitation_data.internal

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.Path
import ru.itmo.edugoolda.data.group.group_invitation_data.internal.dto.GroupInvitationDataDTO
import ru.itmo.edugoolda.data.group.group_invitation_data.internal.dto.JoinGroupDTORequest
import ru.itmo.edugoolda.data.group.group_invitation_data.internal.dto.JoinGroupResponse

internal interface GroupInvitationDataApi {
    @GET("api/v1/group_invitation/{groupId}")
    suspend fun getGroupInvitationData(@Path("groupId") id: String): GroupInvitationDataDTO

    @POST("api/v1/group/join")
    suspend fun sendRequestJoinGroup(@Body code: JoinGroupDTORequest): JoinGroupResponse
}
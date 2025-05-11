package ru.itmo.edugoolda.data.invitations.internal

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Query
import ru.itmo.edugoolda.data.invitations.internal.dto.InvitationListResponse

interface InvitationsApi {
    @GET("api/v1/join_requests/<GROUP_UUID>")
    suspend fun getInvitationList(
        @Query("page_size") pageSize: Int,
        @Query("page") page: Int
    ): InvitationListResponse
}
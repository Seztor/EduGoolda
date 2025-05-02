package ru.itmo.edugoolda.data.invitations.internal

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.Path
import ru.itmo.edugoolda.data.invitations.internal.dto.InvitationActionRequest


interface InvitationApi {
    @POST("/api/v1/join_request/{requestUuid}")
    suspend fun action(@Path("requestUuid") requestId: String, @Body request: InvitationActionRequest, )
}
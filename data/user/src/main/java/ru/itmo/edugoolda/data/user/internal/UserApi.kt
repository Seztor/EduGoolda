package ru.itmo.edugoolda.data.user.internal

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path
import ru.itmo.edugoolda.data.user.internal.dto.ProfileResponse

interface UserApi {
    @GET("api/v1/user/{userId}")
    suspend fun getUserProfile(@Path("userId") id: String): ProfileResponse
}
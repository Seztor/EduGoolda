package ru.itmo.edugoolda.data.lesson.lesson_details.internal

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.Path
import ru.itmo.edugoolda.data.lesson.lesson_details.internal.dto.LessonStudentDetailsResponse
import ru.itmo.edugoolda.data.lesson.lesson_details.internal.dto.SendMessageRequest

internal interface LessonDetailsApi {
    @GET("api/v1/lesson/{lessonId}")
    suspend fun getLessonStudentDetails(@Path("lessonId") lessonId: String): LessonStudentDetailsResponse

    @POST("api/v1/lesson/{lessonId}/message")
    suspend fun sendMessageByStudent(
        @Path("lessonId") lessonId: String,
        @Body message: SendMessageRequest,
    )
}
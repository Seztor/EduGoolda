package ru.itmo.edugoolda.data.lesson.lesson_details.internal

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.PUT
import de.jensklingenberg.ktorfit.http.Path
import ru.itmo.edugoolda.data.lesson.lesson_details.internal.dto.LessonStudentDetailsDTO
import ru.itmo.edugoolda.data.lesson.lesson_details.internal.dto.SendMessageRequest
import ru.itmo.edugoolda.data.lesson.lesson_details.internal.dto.SetSolutionStatusRequest
import ru.itmo.edugoolda.data.lesson.lesson_details.internal.dto.SolutionDetailsDTO

internal interface LessonDetailsApi {
    @GET("api/v1/lesson/{lessonId}")
    suspend fun getLessonStudentDetails(@Path("lessonId") lessonId: String): LessonStudentDetailsDTO

    @GET("api/v1/solution/{solutionId}")
    suspend fun getSolutionTeacherDetails(@Path("solutionId") solutionId: String): SolutionDetailsDTO

    @POST("api/v1/lesson/{lessonId}/message")
    suspend fun sendMessageByStudent(
        @Path("lessonId") lessonId: String,
        @Body message: SendMessageRequest,
    )

    @POST("api/v1/solution/{solutionId}/message")
    suspend fun sendMessageByTeacher(
        @Path("solutionId") solutionId: String,
        @Body message: SendMessageRequest,
    )

    @PUT("api/v1/solution/{solutionId}/status")
    suspend fun setSolutionStatus(
        @Path("solutionId") solutionId: String,
        @Body status: SetSolutionStatusRequest,
    )
}
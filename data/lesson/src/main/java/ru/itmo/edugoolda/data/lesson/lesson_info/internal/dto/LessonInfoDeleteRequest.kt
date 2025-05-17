package ru.itmo.edugoolda.data.lesson.lesson_info.internal.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LessonInfoDeleteRequest(
    @SerialName("action") val action: String
)
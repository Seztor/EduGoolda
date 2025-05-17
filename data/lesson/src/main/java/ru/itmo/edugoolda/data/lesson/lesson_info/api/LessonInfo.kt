package ru.itmo.edugoolda.data.lesson.lesson_info.api

import ru.itmo.edugoolda.data.lesson.lesson_details.api.LessonId
import ru.itmo.edugoolda.data.user.api.UserInfo

data class LessonInfo(
    val id: LessonId,
    val name: String,
    val description: String?,
    val teacher: UserInfo,
    val createdAt: String
)
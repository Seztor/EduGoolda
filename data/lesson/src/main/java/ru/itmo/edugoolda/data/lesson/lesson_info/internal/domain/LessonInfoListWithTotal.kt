package ru.itmo.edugoolda.data.lesson.lesson_info.internal.domain

import ru.itmo.edugoolda.data.lesson.lesson_info.api.LessonInfo

data class LessonInfoListWithTotal(
    val lessonInfoList: List<LessonInfo>,
    val total: Int
)

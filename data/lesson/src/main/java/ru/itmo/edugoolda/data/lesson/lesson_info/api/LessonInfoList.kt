package ru.itmo.edugoolda.data.lesson.lesson_info.api

data class LessonInfoList(
    val lessonInfoList: List<LessonInfo>,
    val hasNextPage: Boolean
)
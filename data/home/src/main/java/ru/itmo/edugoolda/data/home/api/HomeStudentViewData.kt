package ru.itmo.edugoolda.data.home.api

import ru.itmo.edugoolda.data.join_requests.api.JoinRequest
import ru.itmo.edugoolda.data.lesson.lesson_info.api.LessonInfo

data class HomeStudentViewData(
    val joinRequests: List<JoinRequest>,
    val lessonInfoList: List<LessonInfo>
)

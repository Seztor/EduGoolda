package ru.itmo.edugoolda.data.lesson.lesson_details.api

import kotlinx.datetime.Instant
import ru.itmo.edugoolda.data.group.group_info.api.GroupInfo
import ru.itmo.edugoolda.data.user.api.UserInfo

data class LessonGeneralDetails(
    val id: LessonId,
    val name: String,
    val description: String?,
    val teacher: UserInfo,
    val deadline: Instant,
    val groups: List<GroupInfo>,
    val isEstimatable: Boolean,
)
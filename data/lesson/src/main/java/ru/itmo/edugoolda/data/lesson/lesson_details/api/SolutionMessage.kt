package ru.itmo.edugoolda.data.lesson.lesson_details.api

import kotlinx.datetime.Instant
import ru.itmo.edugoolda.data.user.api.UserId
import ru.itmo.edugoolda.data.user.api.UserInfo

data class SolutionMessage(
    val id: SolutionMessageId,
    val sentAt: Instant,
    val message: String,
    val author: UserInfo
)
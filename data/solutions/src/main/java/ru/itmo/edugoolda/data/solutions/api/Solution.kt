package ru.itmo.edugoolda.data.solutions.api

import ru.itmo.edugoolda.data.user.api.UserInfo

data class Solution(
    val id: SolutionId,
    val lessonName: String,
    val sender: UserInfo,
    val date: String
)
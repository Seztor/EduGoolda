package ru.itmo.edugoolda.data.home.api

import ru.itmo.edugoolda.data.invitations.api.JoinRequest
import ru.itmo.edugoolda.data.solutions.api.Solution

data class HomeTeacherViewData(
    val joinRequests: List<JoinRequest>,
    val solutions: List<Solution>
)

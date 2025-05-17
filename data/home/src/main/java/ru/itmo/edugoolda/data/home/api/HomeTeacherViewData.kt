package ru.itmo.edugoolda.data.home.api

import ru.itmo.edugoolda.data.join_requests.api.JoinRequest
import ru.itmo.edugoolda.data.solutions.api.SolutionInfo

data class HomeTeacherViewData(
    val joinRequests: List<JoinRequest>,
    val solutionInfos: List<SolutionInfo>
)

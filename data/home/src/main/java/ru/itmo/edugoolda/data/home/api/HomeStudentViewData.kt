package ru.itmo.edugoolda.data.home.api

import ru.itmo.edugoolda.data.invitations.api.JoinRequest
import ru.itmo.edugoolda.data.solutions.api.SolutionInfo

data class HomeStudentViewData(
    val joinRequests: List<JoinRequest>,
    val solutionInfos: List<SolutionInfo>, // TODO Change List<SolutionInfo> to List<Lessons>
)

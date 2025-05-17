package ru.itmo.edugoolda.data.home.api

import ru.itmo.edugoolda.data.join_requests.api.JoinRequest
import ru.itmo.edugoolda.data.solutions.api.Solution

data class HomeStudentViewData(
    val joinRequests: List<JoinRequest>,
    val solutions: List<Solution>, // TODO Change List<Solution> to List<Lessons>
)

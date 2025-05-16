package ru.itmo.edugoolda.data.home.api

import ru.itmo.edugoolda.data.invitations.api.JoinRequest
import ru.itmo.edugoolda.data.solutions.api.Solution

data class HomeStudentViewData(
    val joinRequests: List<JoinRequest>,
    val solutions: List<Solution>, // TODO Change List<Solution> to List<Lessons>
)

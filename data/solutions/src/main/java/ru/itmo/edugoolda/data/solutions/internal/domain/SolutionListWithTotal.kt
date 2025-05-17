package ru.itmo.edugoolda.data.solutions.internal.domain

import ru.itmo.edugoolda.data.solutions.api.SolutionInfo

data class SolutionListWithTotal(
    val solutionInfoList: List<SolutionInfo>,
    val total: Int
)

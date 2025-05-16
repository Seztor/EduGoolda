package ru.itmo.edugoolda.data.solutions.internal.domain

import ru.itmo.edugoolda.data.solutions.api.Solution

data class SolutionListWithTotal(
    val solutionList: List<Solution>,
    val total: Int
)

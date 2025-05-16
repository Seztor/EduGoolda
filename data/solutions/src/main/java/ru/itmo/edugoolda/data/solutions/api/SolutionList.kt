package ru.itmo.edugoolda.data.solutions.api

data class SolutionList(
    val solutionList: List<Solution>,
    val hasNextPage: Boolean
)
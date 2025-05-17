package ru.itmo.edugoolda.data.solutions.api

data class SolutionInfoList(
    val solutionInfoList: List<SolutionInfo>,
    val hasNextPage: Boolean
)
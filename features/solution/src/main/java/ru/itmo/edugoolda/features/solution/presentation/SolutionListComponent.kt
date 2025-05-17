package ru.itmo.edugoolda.features.solution.presentation

import kotlinx.coroutines.flow.StateFlow
import ru.itmo.edugoolda.core.utils.PagedState
import ru.itmo.edugoolda.data.solutions.api.SolutionInfo
import ru.itmo.edugoolda.data.solutions.api.SolutionInfoList

interface SolutionListComponent {
    val solutionState: StateFlow<PagedState<SolutionInfoList>>

    fun onSolutionClick(solutionInfo: SolutionInfo)
    fun onRefresh()
    fun onRetryClick()
    fun onLoadNext()
    interface Communication {
        fun onSolutionDetailsRequested()
    }
}
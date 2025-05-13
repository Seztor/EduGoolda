package ru.itmo.edugoolda.features.home.presentation.student

import kotlinx.coroutines.flow.StateFlow
import ru.itmo.edugoolda.core.utils.LoadableState
import ru.itmo.edugoolda.core.utils.PagedState
import ru.itmo.edugoolda.data.invitations.api.JoinRequestList
import ru.itmo.edugoolda.data.solutions.api.Solution
import ru.itmo.edugoolda.data.solutions.api.SolutionList


interface HomeStudentComponent {
    val mainState: StateFlow<LoadableState<List<Any>>>
    val joinRequestState: StateFlow<PagedState<JoinRequestList>>
    val solutionState: StateFlow<PagedState<SolutionList>>

    fun onSolutionClick(solution: Solution)
    fun onAllSolutionsClick()
    fun onAllJoinRequestsClick()
    fun onRefresh()
    fun onRetryClick()
    fun onLoadNext()
}
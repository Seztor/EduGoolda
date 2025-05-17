package ru.itmo.edugoolda.features.home.presentation.student

import kotlinx.coroutines.flow.StateFlow
import ru.itmo.edugoolda.core.utils.LoadableState
import ru.itmo.edugoolda.data.home.api.HomeStudentViewData
import ru.itmo.edugoolda.data.solutions.api.SolutionId

interface HomeStudentComponent {
    val mainState: StateFlow<LoadableState<HomeStudentViewData>>

    fun onSolutionClick(solutionId: SolutionId)
    fun onAllSolutionsClick()
    fun onAllJoinRequestsClick()
    fun onRefresh()
    fun onRetryClick()

    interface Communication {
        fun onAllSolutionsRequested()
        fun onAllJoinRequestsRequested()
    }
}
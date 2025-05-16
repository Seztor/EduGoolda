package ru.itmo.edugoolda.features.home.presentation.teacher

import kotlinx.coroutines.flow.StateFlow
import ru.itmo.edugoolda.core.utils.LoadableState
import ru.itmo.edugoolda.data.home.api.HomeTeacherViewData
import ru.itmo.edugoolda.data.invitations.api.JoinRequest
import ru.itmo.edugoolda.data.solutions.api.Solution

interface HomeTeacherComponent {
    val mainState: StateFlow<LoadableState<HomeTeacherViewData>>

    fun onAcceptClick(joinRequest: JoinRequest)
    fun onDeclineClick(joinRequest: JoinRequest)
    fun onRefresh()
    fun onRetryClick()
    fun onSolutionClick(solution: Solution)
    fun onAllSolutionsClick()
    fun onAllJoinRequestsClick()

    interface Communication {
        fun onSolutionDetailsRequested(solution: Solution)
        fun onAllSolutionsRequested()
        fun onAllJoinRequestsRequested()
    }
}
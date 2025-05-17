package ru.itmo.edugoolda.features.home.presentation.teacher

import com.arkivanov.decompose.ComponentContext
import me.aartikov.replica.algebra.normal.combine
import me.aartikov.replica.algebra.paged.toReplica
import ru.itmo.edugoolda.core.error_handling.ErrorHandler
import ru.itmo.edugoolda.core.error_handling.safeLaunch
import ru.itmo.edugoolda.core.utils.componentScope
import ru.itmo.edugoolda.core.utils.observe
import ru.itmo.edugoolda.data.home.api.HomeTeacherViewData
import ru.itmo.edugoolda.data.join_requests.api.JoinRequest
import ru.itmo.edugoolda.data.join_requests.api.JoinRequestAction
import ru.itmo.edugoolda.data.join_requests.api.JoinRequestRepository
import ru.itmo.edugoolda.data.solutions.api.Solution
import ru.itmo.edugoolda.data.solutions.api.SolutionRepository

class HomeTeacherComponentImpl(
    componentContext: ComponentContext,
    private val communication: HomeTeacherComponent.Communication,
    private val errorHandler: ErrorHandler,
    private val joinRequestRepository: JoinRequestRepository,
    solutionRepository: SolutionRepository,

    ) : HomeTeacherComponent, ComponentContext by componentContext {

    private val joinRequestsReplica = joinRequestRepository.joinRequestListReplica.toReplica()
    private val solutionsReplica = solutionRepository.solutionListReplica.toReplica()
    private val mainStateReplica = combine(
        joinRequestsReplica,
        solutionsReplica
    ) { joinRequests, solutions -> HomeTeacherViewData(joinRequests.joinRequestList, solutions.solutionList) }
    override val mainState = mainStateReplica.observe(this, errorHandler)

    override fun onAcceptClick(joinRequest: JoinRequest) {
        componentScope.safeLaunch(errorHandler) {
            joinRequestRepository.respondToJoinRequest(joinRequest.id, JoinRequestAction.Accept)
        }
    }

    override fun onDeclineClick(joinRequest: JoinRequest) {
        componentScope.safeLaunch(errorHandler) {
            joinRequestRepository.respondToJoinRequest(joinRequest.id, JoinRequestAction.Decline)
        }
    }

    override fun onSolutionClick(solution: Solution) {
        communication.onSolutionDetailsRequested(solution.id)
    }

    override fun onAllSolutionsClick() {
        communication.onAllSolutionsRequested()
    }

    override fun onAllJoinRequestsClick() {
        communication.onAllJoinRequestsRequested()
    }

    override fun onRefresh() {
        mainStateReplica.refresh()
    }

    override fun onRetryClick() {
        mainStateReplica.revalidate()
    }
}
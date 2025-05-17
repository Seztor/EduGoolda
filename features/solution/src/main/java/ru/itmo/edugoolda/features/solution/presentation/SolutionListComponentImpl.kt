package ru.itmo.edugoolda.features.solution.presentation

import com.arkivanov.decompose.ComponentContext
import ru.itmo.edugoolda.core.error_handling.ErrorHandler
import ru.itmo.edugoolda.core.utils.observe
import ru.itmo.edugoolda.data.solutions.api.SolutionInfo
import ru.itmo.edugoolda.data.solutions.api.SolutionRepository

abstract class SolutionListComponentImpl(
    componentContext: ComponentContext,
    private val communication: SolutionListComponent.Communication,
    private val errorHandler: ErrorHandler,
    private val solutionRepository: SolutionRepository,

    ) : SolutionListComponent, ComponentContext by componentContext {

    private val solutionReplica = solutionRepository.solutionListReplica
    override val solutionState = solutionReplica.observe(this, errorHandler)
    override fun onSolutionClick(solutionInfo: SolutionInfo) {
        communication.onSolutionDetailsRequested()
    }

    override fun onRefresh() {
        solutionReplica.refresh()
    }

    override fun onRetryClick() {
        solutionReplica.revalidate()
    }

    override fun onLoadNext() {
        solutionReplica.loadNext()
    }
}
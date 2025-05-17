package ru.itmo.edugoolda.features.solution

import com.arkivanov.decompose.ComponentContext
import org.koin.core.component.get
import ru.itmo.edugoolda.core.ComponentFactory
import ru.itmo.edugoolda.features.solution.presentation.SolutionListComponent
import ru.itmo.edugoolda.features.solution.presentation.SolutionListComponentImpl

fun ComponentFactory.createSolutionListComponent(
    componentContext: ComponentContext,
    communication: SolutionListComponent.Communication
): SolutionListComponent {
    return SolutionListComponentImpl(
        componentContext,
        communication,
        get(),
        get(),
    )
}
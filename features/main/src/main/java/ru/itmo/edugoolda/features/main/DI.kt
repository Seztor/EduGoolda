package ru.itmo.edugoolda.features.main

import com.arkivanov.decompose.ComponentContext
import org.koin.core.component.get
import ru.itmo.edugoolda.core.ComponentFactory
import ru.itmo.edugoolda.features.main.presentation.student.MainStudentComponent
import ru.itmo.edugoolda.features.main.presentation.student.RealMainStudentComponent
import ru.itmo.edugoolda.features.main.presentation.teacher.MainTeacherComponent
import ru.itmo.edugoolda.features.main.presentation.teacher.RealMainTeacherComponent

fun ComponentFactory.createMainTeacherComponent(
    componentContext: ComponentContext,
    communication: MainTeacherComponent.Communication
): MainTeacherComponent {
    return RealMainTeacherComponent(
        componentContext,
        communication,
        get(),
    )
}

fun ComponentFactory.createMainStudentComponent(
    componentContext: ComponentContext,
    communication: MainStudentComponent.Communication
): MainStudentComponent {
    return RealMainStudentComponent(
        componentContext,
        communication,
        get(),
    )
}

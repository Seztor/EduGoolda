package ru.itmo.edugoolda.features.home

import com.arkivanov.decompose.ComponentContext
import org.koin.core.component.get
import ru.itmo.edugoolda.core.ComponentFactory
import ru.itmo.edugoolda.features.home.presentation.student.HomeStudentComponent
import ru.itmo.edugoolda.features.home.presentation.student.HomeStudentComponentImpl
import ru.itmo.edugoolda.features.home.presentation.teacher.HomeTeacherComponent
import ru.itmo.edugoolda.features.home.presentation.teacher.HomeTeacherComponentImpl

fun ComponentFactory.createHomeTeacherComponent(
    componentContext: ComponentContext,
): HomeTeacherComponent {
    return HomeTeacherComponentImpl(componentContext, get(), get(), get())
}
fun ComponentFactory.createHomeStudentComponent(
    componentContext: ComponentContext,
): HomeStudentComponent {
    return HomeStudentComponentImpl(componentContext, get(), get(), get())
}


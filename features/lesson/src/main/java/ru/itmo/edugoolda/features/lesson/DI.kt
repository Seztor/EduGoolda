package ru.itmo.edugoolda.features.lesson

import com.arkivanov.decompose.ComponentContext
import org.koin.core.component.get
import ru.itmo.edugoolda.core.ComponentFactory
import ru.itmo.edugoolda.data.lesson.lesson_details.api.LessonId
import ru.itmo.edugoolda.data.lesson.lesson_details.api.SolutionId
import ru.itmo.edugoolda.features.lesson.presentation.studentLessonDetails.RealStudentLessonDetailsComponent
import ru.itmo.edugoolda.features.lesson.presentation.studentLessonDetails.StudentLessonDetailsComponent
import ru.itmo.edugoolda.features.lesson.presentation.teacherSolutionDetails.RealTeacherSolutionDetailsComponent
import ru.itmo.edugoolda.features.lesson.presentation.teacherSolutionDetails.TeacherSolutionDetailsComponent

fun ComponentFactory.createStudentLessonDetailsComponent(
    lessonId: LessonId,
    componentContext: ComponentContext,
    communication: StudentLessonDetailsComponent.Communication,
): RealStudentLessonDetailsComponent {
    return RealStudentLessonDetailsComponent(lessonId, componentContext, communication, get(), get())
}

fun ComponentFactory.createTeacherSolutionDetailsComponent(
    solutionId: SolutionId,
    componentContext: ComponentContext,
    communication: TeacherSolutionDetailsComponent.Communication,
): RealTeacherSolutionDetailsComponent {
    return RealTeacherSolutionDetailsComponent(solutionId, componentContext, communication, get(), get())
}

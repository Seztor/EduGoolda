package ru.itmo.edugoolda.features.lesson

import com.arkivanov.decompose.ComponentContext
import org.koin.core.component.get
import ru.itmo.edugoolda.core.ComponentFactory
import ru.itmo.edugoolda.data.lesson.lesson_details.api.LessonId
import ru.itmo.edugoolda.features.lesson.presentation.studentLessonDetails.RealStudentLessonDetailsComponent
import ru.itmo.edugoolda.features.lesson.presentation.studentLessonDetails.StudentLessonDetailsComponent

fun ComponentFactory.createStudentLessonDetailsComponent(
    lessonId: LessonId,
    componentContext: ComponentContext,
    communication: StudentLessonDetailsComponent.Communication,
): RealStudentLessonDetailsComponent {
    return RealStudentLessonDetailsComponent(lessonId, componentContext, communication, get(), get())
}

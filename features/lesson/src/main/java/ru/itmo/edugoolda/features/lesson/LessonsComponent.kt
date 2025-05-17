package ru.itmo.edugoolda.features.lesson

import com.arkivanov.decompose.router.stack.ChildStack
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.Serializable
import ru.itmo.edugoolda.data.lesson.lesson_details.api.LessonId
import ru.itmo.edugoolda.data.solutions.api.SolutionId
import ru.itmo.edugoolda.features.lesson.presentation.studentLessonDetails.StudentLessonDetailsComponent
import ru.itmo.edugoolda.features.lesson.presentation.teacherLessonDetails.TeacherLessonDetailsComponent
import ru.itmo.edugoolda.features.lesson.presentation.teacherSolutionDetails.TeacherSolutionDetailsComponent

interface LessonsComponent {

    val stack: StateFlow<ChildStack<*, Child>>

    @Serializable
    sealed interface InitialConfiguration {
        @Serializable
        data class StudentLessonDetails(val lessonId: LessonId) : InitialConfiguration

        @Serializable
        data class TeacherLessonDetails(val lessonId: LessonId) : InitialConfiguration

        @Serializable
        data object TeacherLessonInfoList : InitialConfiguration

        @Serializable
        data object TeacherSolutionsInfoList : InitialConfiguration

        @Serializable
        data object TeacherLessonCreate : InitialConfiguration

        @Serializable
        data object StudentLessonInfoList : InitialConfiguration

        @Serializable
        data class TeacherSolutionDetails(val solutionId: SolutionId) : InitialConfiguration
    }

    sealed interface Child {
        data class StudentLessonDetails(val component: StudentLessonDetailsComponent) : Child
        data class TeacherLessonDetails(val component: TeacherLessonDetailsComponent) : Child
        data class TeacherSolutionDetails(val component: TeacherSolutionDetailsComponent) : Child
        data object TeacherLessonInfoList : Child
        data object StudentLessonInfoList : Child
        data object TeacherSolutionsInfoList : Child
        data object TeacherLessonCreate : Child
    }

    interface Communication {
        fun onCancel()
    }
}
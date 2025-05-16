package ru.itmo.edugoolda.features.lesson.presentation.teacherLessonDetails

import kotlinx.coroutines.flow.StateFlow
import ru.itmo.edugoolda.core.dialog.standard.StandardDialogControl
import ru.itmo.edugoolda.core.utils.LoadableState
import ru.itmo.edugoolda.data.lesson.lesson_details.api.LessonFullDetails

interface TeacherLessonDetailsComponent {
    val lessonTeacherDetailsState: StateFlow<LoadableState<LessonFullDetails>>
    val isDeletingLessonProgress: StateFlow<Boolean>
    val dialogDeleteLesson: StandardDialogControl

    fun onRefresh()
    fun onRetryClick()
    fun onReturnBackClick()
    fun onDialogLessonDelete()

    interface Communication {
        fun onReturnBackTeacherLessonDetails()
        fun onTeacherLessonDeleted()
    }
}
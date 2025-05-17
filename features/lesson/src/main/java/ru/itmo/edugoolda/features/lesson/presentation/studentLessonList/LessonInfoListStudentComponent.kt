package ru.itmo.edugoolda.features.lesson.presentation.studentLessonList

import kotlinx.coroutines.flow.StateFlow
import ru.itmo.edugoolda.core.utils.PagedState
import ru.itmo.edugoolda.data.lesson.lesson_details.api.LessonId
import ru.itmo.edugoolda.data.lesson.lesson_info.api.LessonInfoList

interface LessonInfoListStudentComponent {
    val lessonInfoState: StateFlow<PagedState<LessonInfoList>>

    fun onLessonClick(lessonId: LessonId)
    fun onRefresh()
    fun onRetryClick()
    fun onLoadNext()
    interface Communication {
        fun onLessonDetailsRequested(lessonId: LessonId)
    }
}
package ru.itmo.edugoolda.features.lesson.presentation.studentLessonList

import kotlinx.coroutines.flow.StateFlow
import ru.itmo.edugoolda.core.utils.PagedState
import ru.itmo.edugoolda.data.lesson.lesson_info.api.LessonInfo
import ru.itmo.edugoolda.data.lesson.lesson_info.api.LessonInfoList

interface LessonInfoListStudentComponent {
    val lessonInfoState: StateFlow<PagedState<LessonInfoList>>

    fun onLessonClick(lessonInfo: LessonInfo)
    fun onRefresh()
    fun onRetryClick()
    fun onLoadNext()
    interface Communication {
        fun onLessonDetailsRequested()
    }
}
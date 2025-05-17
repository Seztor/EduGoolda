package ru.itmo.edugoolda.features.home.presentation.student

import kotlinx.coroutines.flow.StateFlow
import ru.itmo.edugoolda.core.utils.LoadableState
import ru.itmo.edugoolda.data.home.api.HomeStudentViewData
import ru.itmo.edugoolda.data.lesson.lesson_info.api.LessonInfo

interface HomeStudentComponent {
    val mainState: StateFlow<LoadableState<HomeStudentViewData>>

    fun onLessonClick(lessonInfo: LessonInfo)
    fun onAllSolutionsClick()
    fun onAllJoinRequestsClick()
    fun onRefresh()
    fun onRetryClick()

    interface Communication {
        fun onLessonDetailsRequested(lessonInfo: LessonInfo)
        fun onAllSolutionsRequested()
        fun onAllJoinRequestsRequested()
    }
}
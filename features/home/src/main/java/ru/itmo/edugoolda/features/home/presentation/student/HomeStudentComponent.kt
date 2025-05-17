package ru.itmo.edugoolda.features.home.presentation.student

import kotlinx.coroutines.flow.StateFlow
import ru.itmo.edugoolda.core.utils.LoadableState
import ru.itmo.edugoolda.data.home.api.HomeStudentViewData
import ru.itmo.edugoolda.data.lesson.lesson_details.api.LessonId

interface HomeStudentComponent {
    val mainState: StateFlow<LoadableState<HomeStudentViewData>>

    fun onLessonClick(lessonId: LessonId)
    fun onAllSolutionsClick()
    fun onAllJoinRequestsClick()
    fun onRefresh()
    fun onRetryClick()

    interface Communication {
        fun onLessonDetailsRequested(lessonId: LessonId)
        fun onAllSolutionsRequested()
        fun onAllJoinRequestsRequested()
    }
}
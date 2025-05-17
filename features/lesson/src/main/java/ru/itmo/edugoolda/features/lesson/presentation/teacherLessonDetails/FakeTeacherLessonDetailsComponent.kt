package ru.itmo.edugoolda.features.lesson.presentation.teacherLessonDetails

import kotlinx.coroutines.flow.MutableStateFlow
import ru.itmo.edugoolda.core.dialog.standard.fakeStandardDialogControl
import ru.itmo.edugoolda.core.utils.LoadableState
import ru.itmo.edugoolda.data.lesson.lesson_details.api.LessonFullDetails

class FakeTeacherLessonDetailsComponent : TeacherLessonDetailsComponent {
    override val lessonTeacherDetailsState = MutableStateFlow(LoadableState(data = LessonFullDetails.MOCK))
    override val isDeletingLessonProgress = MutableStateFlow(false)
    override val dialogDeleteLesson = fakeStandardDialogControl()

    override fun onRefresh() = Unit
    override fun onRetryClick() = Unit
    override fun onReturnBackClick() = Unit
    override fun onDialogLessonDelete() = Unit
}
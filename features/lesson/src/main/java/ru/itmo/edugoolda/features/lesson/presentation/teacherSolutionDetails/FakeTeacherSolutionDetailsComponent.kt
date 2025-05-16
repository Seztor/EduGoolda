package ru.itmo.edugoolda.features.lesson.presentation.teacherSolutionDetails

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.itmo.edugoolda.core.utils.LoadableState
import ru.itmo.edugoolda.data.lesson.lesson_details.api.LessonStatus
import ru.itmo.edugoolda.data.lesson.lesson_details.api.SolutionDetails
import ru.mobileup.kmm_form_validation.control.InputControl

class FakeTeacherSolutionDetailsComponent : TeacherSolutionDetailsComponent {
    override val replyInputControl = InputControl(GlobalScope)
    override val isSendingMessageProgress = MutableStateFlow(false)
    override val isChangingSolutionStatus = MutableStateFlow(false)
    override val isReplyButtonEnabled = MutableStateFlow(false)
    override val solutionTeacherDetailsState: StateFlow<LoadableState<SolutionDetails>> = MutableStateFlow(LoadableState(data = SolutionDetails.MOCK))

    override fun onRefresh() = Unit
    override fun onRetryClick() = Unit
    override fun onSendCommentClick() = Unit
    override fun onSetSolutionStatus(status: LessonStatus) = Unit
    override fun onReturnBackClick() = Unit
}
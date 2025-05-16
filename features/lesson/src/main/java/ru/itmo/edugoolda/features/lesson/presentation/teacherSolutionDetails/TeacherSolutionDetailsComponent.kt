package ru.itmo.edugoolda.features.lesson.presentation.teacherSolutionDetails

import kotlinx.coroutines.flow.StateFlow
import ru.itmo.edugoolda.core.utils.LoadableState
import ru.itmo.edugoolda.data.lesson.lesson_details.api.LessonStatus
import ru.itmo.edugoolda.data.lesson.lesson_details.api.SolutionDetails
import ru.mobileup.kmm_form_validation.control.InputControl

interface TeacherSolutionDetailsComponent {
    val replyInputControl: InputControl
    val isSendingMessageProgress: StateFlow<Boolean>
    val isChangingSolutionStatus: StateFlow<Boolean>
    val isReplyButtonEnabled: StateFlow<Boolean>
    val solutionTeacherDetailsState: StateFlow<LoadableState<SolutionDetails>>

    fun onRefresh()
    fun onRetryClick()
    fun onSendCommentClick(message: String)
    fun onSetSolutionStatus(status: LessonStatus)
    fun onReturnBackClick()

    interface Communication {
        fun onReturnBackTeacherSolutionDetails()
    }
}
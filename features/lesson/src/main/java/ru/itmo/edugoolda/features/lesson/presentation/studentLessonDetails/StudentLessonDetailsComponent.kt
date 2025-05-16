package ru.itmo.edugoolda.features.lesson.presentation.studentLessonDetails

import kotlinx.coroutines.flow.StateFlow
import ru.itmo.edugoolda.core.utils.LoadableState
import ru.itmo.edugoolda.data.lesson.lesson_details.api.LessonStudentDetails
import ru.mobileup.kmm_form_validation.control.InputControl

interface StudentLessonDetailsComponent {
    val replyInputControl: InputControl
    val isSendingMessageProgress: StateFlow<Boolean>
    val isReplyButtonEnabled: StateFlow<Boolean>
    val lessonStudentDetailsState: StateFlow<LoadableState<LessonStudentDetails>>

    fun onRefresh()
    fun onRetryClick()
    fun onSendCommentClick()
    fun onReturnBackClick()

    interface Communication {
        fun onReturnBackStudentLessonDetails()
    }
}
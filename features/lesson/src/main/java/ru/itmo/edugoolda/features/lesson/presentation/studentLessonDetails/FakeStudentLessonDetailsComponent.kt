package ru.itmo.edugoolda.features.lesson.presentation.studentLessonDetails

import androidx.compose.material3.IconButton
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.itmo.edugoolda.core.utils.LoadableState
import ru.itmo.edugoolda.data.lesson.lesson_details.api.LessonStudentDetails
import ru.mobileup.kmm_form_validation.control.InputControl

class FakeStudentLessonDetailsComponent : StudentLessonDetailsComponent{
    override val replyInputControl = InputControl(GlobalScope)
    override val isSendingMessageProgress = MutableStateFlow(false)
    override val isReplyButtonEnabled = MutableStateFlow(false)
    override val lessonStudentDetailsState = MutableStateFlow(LoadableState(data = LessonStudentDetails.MOCK))

    override fun onRefresh() = Unit

    override fun onRetryClick() = Unit

    override fun onSendCommentClick(message: String) = Unit

    override fun onReturnBackClick() = Unit
}
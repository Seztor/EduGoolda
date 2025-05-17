package ru.itmo.edugoolda.features.lesson.presentation.createLesson

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import ru.itmo.edugoolda.data.group.group_info.api.GroupInfo
import ru.itmo.edugoolda.data.group.group_list.api.GroupId
import ru.itmo.edugoolda.data.lesson.lesson_details.api.LessonType
import ru.mobileup.kmm_form_validation.control.InputControl

class FakeCreateLessonComponent : CreateLessonComponent {
    override val lessonNameInputControl = InputControl(GlobalScope)
    override val descriptionInputControl = InputControl(GlobalScope)
    override val selectedLessonType = MutableStateFlow(LessonType.Practical)
    override val isCreatingLessonProgress = MutableStateFlow(false)
    override val isCreateLessonButtonEnabled = MutableStateFlow(false)
    override val groupListState = MutableStateFlow(listOf(GroupInfo.MOCK, GroupInfo.MOCK, GroupInfo.MOCK, GroupInfo.MOCK, GroupInfo.MOCK, GroupInfo.MOCK))

    override fun onCreateLesson() = Unit
    override fun onReturnBackClick() = Unit
    override fun onLessonTypeSelect(newType: LessonType) = Unit
    override fun onGroupAdded(groupInfo: GroupInfo) = Unit
    override fun onAddGroupButtonClick() = Unit
    override fun onGroupDeleteClick(groupId: GroupId) = Unit
}
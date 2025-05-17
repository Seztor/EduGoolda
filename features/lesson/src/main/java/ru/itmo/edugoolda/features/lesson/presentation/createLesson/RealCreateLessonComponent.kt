package ru.itmo.edugoolda.features.lesson.presentation.createLesson

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.itmo.edugoolda.core.error_handling.ErrorHandler
import ru.itmo.edugoolda.core.utils.componentScope
import ru.itmo.edugoolda.core.utils.computed
import ru.itmo.edugoolda.data.group.group_info.api.GroupInfo
import ru.itmo.edugoolda.data.lesson.lesson_details.api.LessonDetailsRepository
import ru.itmo.edugoolda.data.lesson.lesson_details.api.LessonType
import ru.mobileup.kmm_form_validation.control.InputControl

class RealCreateLessonComponent(
    componentContext: ComponentContext,
    private val communication: CreateLessonComponent.Communication,
    private val errorHandler: ErrorHandler,
    private val lessonDetailsRepository: LessonDetailsRepository,
) : CreateLessonComponent, ComponentContext by componentContext {
    override val lessonNameInputControl = InputControl(componentScope)
    override val descriptionInputControl = InputControl(componentScope)
    override val selectedLessonType = MutableStateFlow(LessonType.Informational)
    override val isCreatingLessonProgress = MutableStateFlow(false)
    override val groupListState: StateFlow<List<GroupInfo>> = TODO()
    override val isCreateLessonButtonEnabled =
        computed(lessonNameInputControl.text, descriptionInputControl.text, groupListState) { text, description, groupList ->
            text.isNotBlank() && description.isNotBlank() && groupList.isNotEmpty()
        }


    override fun onCreateLesson() {
        TODO("Not yet implemented")
    }

    override fun onReturnBackClick() {
        communication.onCancelLessonCreation()
    }

    override fun onLessonTypeSelect(newType: LessonType) {
        selectedLessonType.value = newType
    }

    override fun onGroupAdded(groupInfo: GroupInfo) {
        TODO("Not yet implemented")
    }

    override fun onAddGroupButtonClick() {
        communication.onGroupAddRequested()
    }
}
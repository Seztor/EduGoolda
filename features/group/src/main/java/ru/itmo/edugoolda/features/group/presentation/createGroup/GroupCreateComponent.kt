package ru.itmo.edugoolda.features.group.presentation.createGroup

import kotlinx.coroutines.flow.StateFlow
import ru.itmo.edugoolda.data.group.group_list.api.GroupId
import ru.mobileup.kmm_form_validation.control.InputControl

interface GroupCreateComponent {
    val nameInputControl: InputControl
    val descriptionInputControl: InputControl
    val subjectInputControl: InputControl
    val isCreationProgress: StateFlow<Boolean>
    val isCreationButtonEnabled: StateFlow<Boolean>

    fun onCreateClick()
    fun onCancelClick()

    interface Communication {
        fun onGroupCreated()
        fun onCancelGroupCreation()
    }
}
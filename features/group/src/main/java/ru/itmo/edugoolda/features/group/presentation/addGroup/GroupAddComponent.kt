package ru.itmo.edugoolda.features.group.presentation.addGroup

import kotlinx.coroutines.flow.StateFlow
import ru.itmo.edugoolda.data.group.group_list.api.GroupId
import ru.mobileup.kmm_form_validation.control.InputControl

interface GroupAddComponent {
    val codeInputControl: InputControl
    val isAddingProgress: StateFlow<Boolean>
    val isAddButtonEnabled: StateFlow<Boolean>

    fun onAddClick()
    fun onCancelClick()

    interface Communication {
        fun onGroupAdded()
        fun onCancelGroupAdding()
    }
}
package ru.itmo.edugoolda.features.group.presentation.addGroup

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import ru.mobileup.kmm_form_validation.control.InputControl

class FakeGroupAddComponent : GroupAddComponent {
    override val codeInputControl = InputControl(GlobalScope)
    override val isAddingProgress = MutableStateFlow(false)
    override val isAddButtonEnabled = MutableStateFlow(false)

    override fun onAddClick() = Unit
    override fun onCancelClick() = Unit
}
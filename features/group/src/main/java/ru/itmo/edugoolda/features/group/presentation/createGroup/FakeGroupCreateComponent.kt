package ru.itmo.edugoolda.features.group.presentation.createGroup

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import ru.mobileup.kmm_form_validation.control.InputControl

class FakeGroupCreateComponent() : GroupCreateComponent {
    override val nameInputControl = InputControl(GlobalScope)
    override val descriptionInputControl = InputControl(GlobalScope)
    override val subjectInputControl = InputControl(GlobalScope)
    override val isCreationProgress = MutableStateFlow(false)
    override val isCreationButtonEnabled = MutableStateFlow(true)

    override fun onCreateClick() = Unit

    override fun onCancelClick() = Unit
}
package ru.itmo.edugoolda.features.group.presentation.addGroup

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.itmo.edugoolda.core.error_handling.ErrorHandler
import ru.itmo.edugoolda.core.error_handling.safeLaunch
import ru.itmo.edugoolda.core.utils.componentScope
import ru.itmo.edugoolda.core.utils.computed
import ru.itmo.edugoolda.core.utils.withProgress
import ru.itmo.edugoolda.data.group.create_group.api.GroupCreateRepository
import ru.itmo.edugoolda.data.group.group_info.api.SubjectId
import ru.itmo.edugoolda.data.group.group_invitation_data.api.GroupInvitationCode
import ru.itmo.edugoolda.data.group.group_invitation_data.api.GroupInvitationDataRepository
import ru.mobileup.kmm_form_validation.control.InputControl

class FakeGroupAddComponent : GroupAddComponent{
    override val codeInputControl = InputControl(GlobalScope)
    override val isAddingProgress = MutableStateFlow(false)
    override val isAddButtonEnabled = MutableStateFlow(false)

    override fun onAddClick() = Unit
    override fun onCancelClick() = Unit
}
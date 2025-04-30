package ru.itmo.edugoolda.features.group.presentation.studentGroups

import kotlinx.coroutines.flow.StateFlow
import ru.itmo.edugoolda.core.utils.PagedState
import ru.itmo.edugoolda.data.group.studentGroups.api.StudentGroupId
import ru.itmo.edugoolda.data.group.studentGroups.api.StudentGroups
import ru.mobileup.kmm_form_validation.control.InputControl

interface StudentGroupComponent {
    val groupSearchInputControl: InputControl
    val studentGroupState: StateFlow<PagedState<StudentGroups>>

    fun onRefresh()
    fun onRetryClick()
    fun onLoadNext()
    fun onGroupDetailRequestClick(id: StudentGroupId)

    interface Communication {
        fun onGroupDetailsRequested(id: StudentGroupId)
    }
}
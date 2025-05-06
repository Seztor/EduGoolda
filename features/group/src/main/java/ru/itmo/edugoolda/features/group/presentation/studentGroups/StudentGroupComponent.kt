package ru.itmo.edugoolda.features.group.presentation.studentGroups

import kotlinx.coroutines.flow.StateFlow
import ru.itmo.edugoolda.core.utils.PagedState
import ru.itmo.edugoolda.data.group.groupList.api.GroupId
import ru.itmo.edugoolda.data.group.groupList.api.GroupList
import ru.mobileup.kmm_form_validation.control.InputControl

interface StudentGroupComponent {
    val groupSearchInputControl: InputControl
    val studentGroupState: StateFlow<PagedState<GroupList>>

    fun onRefresh()
    fun onRetryClick()
    fun onLoadNext()
    fun onGroupDetailRequestClick(id: GroupId)

    interface Communication {
        fun onGroupDetailsRequested(id: GroupId)
    }
}
package ru.itmo.edugoolda.features.group.presentation.teacherGroups

import kotlinx.coroutines.flow.StateFlow
import ru.itmo.edugoolda.core.utils.PagedState
import ru.itmo.edugoolda.data.group.groupList.api.GroupId
import ru.itmo.edugoolda.data.group.groupList.api.GroupList
import ru.mobileup.kmm_form_validation.control.InputControl

interface TeacherGroupComponent {
    val groupSearchInputControl: InputControl
    val teacherGroupState: StateFlow<PagedState<GroupList>>

    fun onRefresh()
    fun onRetryClick()
    fun onLoadNext()
    fun onGroupDetailRequestClick(id: GroupId)
    fun onGroupChangeFavouriteStatusRequestClick(id: GroupId)

    interface Communication {
        fun onGroupDetailsRequested(id: GroupId)
        fun onGroupChangeFavouriteStatusRequested(id: GroupId)
    }
}
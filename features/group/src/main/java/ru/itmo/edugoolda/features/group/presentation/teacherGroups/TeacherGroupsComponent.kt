package ru.itmo.edugoolda.features.group.presentation.teacherGroups

import kotlinx.coroutines.flow.StateFlow
import ru.itmo.edugoolda.core.utils.PagedState
import ru.itmo.edugoolda.data.group.group_list.api.GroupId
import ru.itmo.edugoolda.data.group.group_list.api.GroupInfoList
import ru.mobileup.kmm_form_validation.control.InputControl

interface TeacherGroupsComponent {
    val groupSearchInputControl: InputControl
    val teacherGroupState: StateFlow<PagedState<GroupInfoList>>
    val isChangingFavouriteStatus: StateFlow<Boolean>

    fun onRefresh()
    fun onRetryClick()
    fun onLoadNext()
    fun onGroupDetailsRequestClick(id: GroupId)
    fun onGroupChangeFavouriteStatusRequestClick(id: GroupId, isFavourite: Boolean)

    interface Communication {
        fun onGroupDetailsRequested(id: GroupId)
    }
}
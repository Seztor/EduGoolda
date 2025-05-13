package ru.itmo.edugoolda.features.group.presentation.teacherGroups

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.itmo.edugoolda.core.utils.PagedState
import ru.itmo.edugoolda.data.group.group_list.api.GroupId
import ru.itmo.edugoolda.data.group.group_list.api.GroupInfoList
import ru.mobileup.kmm_form_validation.control.InputControl

class FakeTeacherGroupComponent() : TeacherGroupComponent {
    override val groupSearchInputControl = InputControl(GlobalScope)
    override val teacherGroupState = MutableStateFlow(PagedState(data = GroupInfoList.MOCK))
    override val isChangingFavouriteStatus = MutableStateFlow(false)

    override fun onRefresh() = Unit

    override fun onRetryClick() = Unit

    override fun onLoadNext() = Unit

    override fun onGroupDetailsRequestClick(id: GroupId) = Unit

    override fun onGroupChangeFavouriteStatusRequestClick(id: GroupId, isFavourite: Boolean) = Unit
}
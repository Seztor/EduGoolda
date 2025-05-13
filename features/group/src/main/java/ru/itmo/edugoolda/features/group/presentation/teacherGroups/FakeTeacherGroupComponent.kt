package ru.itmo.edugoolda.features.group.presentation.teacherGroups

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import ru.itmo.edugoolda.core.utils.PagedState
import ru.itmo.edugoolda.data.group.groupList.api.GroupId
import ru.itmo.edugoolda.data.group.groupList.api.GroupList
import ru.mobileup.kmm_form_validation.control.InputControl

class FakeTeacherGroupComponent() : TeacherGroupComponent {
    override val groupSearchInputControl = InputControl(GlobalScope)
    override val teacherGroupState = MutableStateFlow(PagedState(data = GroupList.MOCK))

    override fun onRefresh() = Unit

    override fun onRetryClick() = Unit

    override fun onLoadNext() = Unit

    override fun onGroupDetailRequestClick(id: GroupId) = Unit

    override fun onGroupChangeFavouriteStatusRequestClick(id: GroupId) = Unit
}
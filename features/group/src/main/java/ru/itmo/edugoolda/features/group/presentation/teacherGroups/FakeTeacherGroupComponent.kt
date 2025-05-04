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

    override fun onRefresh() {
        TODO("Not yet implemented")
    }

    override fun onRetryClick() {
        TODO("Not yet implemented")
    }

    override fun onLoadNext() {
        TODO("Not yet implemented")
    }

    override fun onGroupDetailRequestClick(id: GroupId) {
        TODO("Not yet implemented")
    }

    override fun onGroupChangeFavouriteStatusRequestClick(id: GroupId) {
        TODO("Not yet implemented")
    }

    override fun onGroupCreateRequestClick() {
        TODO("Not yet implemented")
    }
}
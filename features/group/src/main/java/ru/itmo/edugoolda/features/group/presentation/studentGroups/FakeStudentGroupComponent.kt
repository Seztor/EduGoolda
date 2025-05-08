package ru.itmo.edugoolda.features.group.presentation.studentGroups

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import ru.itmo.edugoolda.core.utils.PagedState
import ru.itmo.edugoolda.data.group.group_list.api.GroupId
import ru.itmo.edugoolda.data.group.group_list.api.GroupInfoList
import ru.mobileup.kmm_form_validation.control.InputControl

class FakeStudentGroupComponent() : StudentGroupComponent {
    override val groupSearchInputControl = InputControl(GlobalScope)
    override val studentGroupState = MutableStateFlow(PagedState(data = GroupInfoList.MOCK))

    override fun onRefresh() = Unit

    override fun onRetryClick() = Unit

    override fun onLoadNext() = Unit

    override fun onGroupDetailRequestClick(id: GroupId) = Unit

    override fun onGroupAddRequestClick() = Unit
}
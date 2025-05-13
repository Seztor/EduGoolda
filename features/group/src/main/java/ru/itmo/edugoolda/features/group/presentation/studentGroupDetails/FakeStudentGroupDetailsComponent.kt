package ru.itmo.edugoolda.features.group.presentation.studentGroupDetails

import kotlinx.coroutines.flow.MutableStateFlow
import ru.itmo.edugoolda.core.utils.LoadableState
import ru.itmo.edugoolda.data.group.group_info.api.GroupFullInfo

class FakeStudentGroupDetailsComponent() : StudentGroupDetailsComponent {
    override val groupInfoState = MutableStateFlow(LoadableState(data = GroupFullInfo.MOCK))
    override val isQuitingGroupProgress = MutableStateFlow(false)

    override fun onRefresh() = Unit

    override fun onRetryClick() = Unit

    override fun onReturnBackRequestClick() = Unit
    override fun onGroupQuitRequestClick() = Unit
}
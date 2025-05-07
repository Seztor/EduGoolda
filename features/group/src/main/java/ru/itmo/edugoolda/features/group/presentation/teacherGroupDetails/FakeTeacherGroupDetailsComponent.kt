package ru.itmo.edugoolda.features.group.presentation.teacherGroupDetails

import kotlinx.coroutines.flow.MutableStateFlow
import ru.itmo.edugoolda.core.utils.LoadableState
import ru.itmo.edugoolda.core.utils.PagedState
import ru.itmo.edugoolda.data.group.groupInfo.api.GroupFullInfo
import ru.itmo.edugoolda.data.group.groupInvitationData.api.GroupInvitationData
import ru.itmo.edugoolda.data.group.groupList.api.GroupId
import ru.itmo.edugoolda.data.group.groupOfStudentsList.api.GroupOfStudentsList
import ru.itmo.edugoolda.data.user.api.UserId

class FakeTeacherGroupDetailsComponent() : TeacherGroupDetailsComponent {
    override val groupOfStudentsState = MutableStateFlow(PagedState(data = GroupOfStudentsList.MOCK))
    override val groupInvitationDataState = MutableStateFlow(GroupInvitationData.MOCK)
    override val groupInfoState = MutableStateFlow(LoadableState(data = GroupFullInfo.MOCK))
    override val isGettingCodeProgress = MutableStateFlow(false)

    override fun onRefresh() {
        TODO("Not yet implemented")
    }

    override fun onRetryClick() {
        TODO("Not yet implemented")
    }

    override fun onLoadNext() {
        TODO("Not yet implemented")
    }

    override fun onReturnBackRequestClick() {
        TODO("Not yet implemented")
    }

    override fun onGroupDeleteRequestClick(id: GroupId) {
        TODO("Not yet implemented")
    }

    override fun onGroupMemberDeleteRequestClick(id: UserId) {
        TODO("Not yet implemented")
    }

    override fun onGroupCodeGenerateRequestClick(id: GroupId) {
        TODO("Not yet implemented")
    }
}
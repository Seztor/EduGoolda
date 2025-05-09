package ru.itmo.edugoolda.features.group.presentation.teacherGroupDetails

import kotlinx.coroutines.flow.MutableStateFlow
import ru.itmo.edugoolda.core.utils.LoadableState
import ru.itmo.edugoolda.core.utils.PagedState
import ru.itmo.edugoolda.data.group.group_info.api.GroupFullInfo
import ru.itmo.edugoolda.data.group.group_invitation_data.api.GroupInvitationData
import ru.itmo.edugoolda.data.group.group_of_students_list.api.GroupOfStudentsList
import ru.itmo.edugoolda.data.group.group_of_students_list.api.KickType
import ru.itmo.edugoolda.data.user.api.UserId

class FakeTeacherGroupDetailsComponent() : TeacherGroupDetailsComponent {
    override val groupOfStudentsState = MutableStateFlow(PagedState(data = GroupOfStudentsList.MOCK))
    override val groupInvitationDataState = MutableStateFlow(GroupInvitationData.MOCK)
    override val groupInfoState = MutableStateFlow(LoadableState(data = GroupFullInfo.MOCK))
    override val isGettingCodeProgress = MutableStateFlow(false)
    override val isKickingMemberProgress = MutableStateFlow(false)
    override val isDeletingGroupProgress = MutableStateFlow(false)

    override fun onRefresh() = Unit

    override fun onRetryClick() = Unit

    override fun onLoadNext() = Unit

    override fun onReturnBackRequestClick() = Unit

    override fun onGroupDeleteRequestClick() = Unit

    override fun onGroupMemberKickRequestClick(action: KickType, userId: UserId) = Unit

    override fun onGroupCodeGenerateRequestClick() = Unit
}
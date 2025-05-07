package ru.itmo.edugoolda.features.group.presentation.teacherGroupDetails

import kotlinx.coroutines.flow.StateFlow
import ru.itmo.edugoolda.core.utils.LoadableState
import ru.itmo.edugoolda.core.utils.PagedState
import ru.itmo.edugoolda.data.group.groupInfo.api.GroupFullInfo
import ru.itmo.edugoolda.data.group.groupInvitationData.api.GroupInvitationData
import ru.itmo.edugoolda.data.group.groupList.api.GroupId
import ru.itmo.edugoolda.data.group.groupOfStudentsList.api.GroupOfStudentsList
import ru.itmo.edugoolda.data.user.api.UserId

interface TeacherGroupDetailsComponent {
    val groupOfStudentsState: StateFlow<PagedState<GroupOfStudentsList>>
    val groupInvitationDataState: StateFlow<GroupInvitationData>
    val groupInfoState: StateFlow<LoadableState<GroupFullInfo>>
    val isGettingCodeProgress: StateFlow<Boolean>

    fun onRefresh()
    fun onRetryClick()
    fun onLoadNext()
    fun onReturnBackRequestClick()
    fun onGroupDeleteRequestClick(id: GroupId)
    fun onGroupMemberDeleteRequestClick(id: UserId)
    fun onGroupCodeGenerateRequestClick(id: GroupId)

    interface Communication {
        fun onReturnBackRequested()
        fun onGroupDeleteRequested(id: GroupId)
        fun onGroupMemberDeleteRequested(id: UserId)
    }
}
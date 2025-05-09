package ru.itmo.edugoolda.features.group.presentation.teacherGroupDetails

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.MutableStateFlow
import me.aartikov.replica.algebra.normal.withKey
import me.aartikov.replica.algebra.paged.withKey
import ru.itmo.edugoolda.core.error_handling.ErrorHandler
import ru.itmo.edugoolda.core.error_handling.safeLaunch
import ru.itmo.edugoolda.core.utils.componentScope
import ru.itmo.edugoolda.core.utils.observe
import ru.itmo.edugoolda.core.utils.withProgress
import ru.itmo.edugoolda.data.group.group_info.api.GroupFullInfoRepository
import ru.itmo.edugoolda.data.group.group_invitation_data.api.GroupInvitationData
import ru.itmo.edugoolda.data.group.group_invitation_data.api.GroupInvitationDataRepository
import ru.itmo.edugoolda.data.group.group_list.api.GroupId
import ru.itmo.edugoolda.data.group.group_list.api.GroupListRepository
import ru.itmo.edugoolda.data.group.group_of_students_list.api.GroupOfStudentsRepository
import ru.itmo.edugoolda.data.group.group_of_students_list.api.KickType
import ru.itmo.edugoolda.data.user.api.UserId

class RealTeacherGroupDetailsComponent(
    private val groupId: GroupId,
    componentContext: ComponentContext,
    private val communication: TeacherGroupDetailsComponent.Communication,
    private val errorHandler: ErrorHandler,
    private val repositoryGroupOfStudents: GroupOfStudentsRepository,
    private val repositoryGroupFullInfo: GroupFullInfoRepository,
    private val repositoryGroupInvitationData: GroupInvitationDataRepository,
    private val repositoryGroupList: GroupListRepository,
) : TeacherGroupDetailsComponent, ComponentContext by componentContext {
    private val groupOfStudentsReplica =
        repositoryGroupOfStudents.groupOfStudentsReplica.withKey(groupId)
    override val groupOfStudentsState = groupOfStudentsReplica.observe(this, errorHandler)

    private val groupInfoReplica =
        repositoryGroupFullInfo.groupInfoReplica.withKey(groupId)
    override val groupInfoState = groupInfoReplica.observe(this, errorHandler)

    override val groupInvitationDataState: MutableStateFlow<GroupInvitationData?> =
        MutableStateFlow(null)
    override val isGettingCodeProgress = MutableStateFlow(false)
    override val isKickingMemberProgress = MutableStateFlow(false)
    override val isDeletingGroupProgress = MutableStateFlow(false)

    override fun onRefresh() {
        groupOfStudentsReplica.refresh()
        groupInfoReplica.refresh()
    }

    override fun onRetryClick() {
        groupOfStudentsReplica.revalidate()
        groupInfoReplica.revalidate()
    }

    override fun onLoadNext() {
        groupOfStudentsReplica.loadNext()
    }

    override fun onReturnBackRequestClick() {
        communication.onReturnBackRequested()
    }

    override fun onGroupDeleteRequestClick() {
        if (isDeletingGroupProgress.value) return

        componentScope.safeLaunch(errorHandler) {
            withProgress(isDeletingGroupProgress) {
                repositoryGroupList.deleteGroup(groupId)
            }
            communication.onGroupDeleteRequested(groupId)
        }
    }

    override fun onGroupMemberKickRequestClick(action: KickType, userId: UserId) {
        if (isKickingMemberProgress.value) return

        componentScope.safeLaunch(errorHandler) {
            withProgress(isKickingMemberProgress) {
                repositoryGroupOfStudents.kickStudentFromGroup(action, groupId, userId)
            }
        }
    }

    override fun onGroupCodeGenerateRequestClick() {
        if (isGettingCodeProgress.value) return

        componentScope.safeLaunch(errorHandler) {
            withProgress(isGettingCodeProgress) {
                val groupInvitationData =
                    repositoryGroupInvitationData.getGroupInvitationData(groupId)
                groupInvitationDataState.value = groupInvitationData
            }
        }
    }
}
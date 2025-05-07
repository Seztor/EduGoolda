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
import ru.itmo.edugoolda.data.group.groupInfo.api.GroupFullInfoRepository
import ru.itmo.edugoolda.data.group.groupInvitationData.api.GroupInvitationCode
import ru.itmo.edugoolda.data.group.groupInvitationData.api.GroupInvitationData
import ru.itmo.edugoolda.data.group.groupInvitationData.api.GroupInvitationDataRepository
import ru.itmo.edugoolda.data.group.groupList.api.GroupId
import ru.itmo.edugoolda.data.group.groupOfStudentsList.api.GroupOfStudentsRepository
import ru.itmo.edugoolda.data.user.api.UserId

class RealTeacherGroupDetailsComponent(
    componentContext: ComponentContext,
    private val communication: TeacherGroupDetailsComponent.Communication,
    private val errorHandler: ErrorHandler,
    private val repositoryGroupOfStudentsRepository: GroupOfStudentsRepository,
    private val repositoryGroupFullInfo: GroupFullInfoRepository,
    private val repositoryGroupInvitationDataRepository: GroupInvitationDataRepository
    ) : TeacherGroupDetailsComponent, ComponentContext by componentContext {
    private val groupOfStudentsReplica = repositoryGroupOfStudentsRepository.groupOfStudentsReplica.withKey(GroupId("text"))
    override val groupOfStudentsState = groupOfStudentsReplica.observe(this, errorHandler)

    private val groupInfoReplica = repositoryGroupFullInfo.groupInfoReplica.withKey(GroupId("text2"))
    override val groupInfoState = groupInfoReplica.observe(this, errorHandler)

    override val groupInvitationDataState = MutableStateFlow(GroupInvitationData(GroupInvitationCode(""), ""))
    override val isGettingCodeProgress = MutableStateFlow(false)

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

    override fun onGroupDeleteRequestClick(id: GroupId) {
        communication.onGroupDeleteRequested(id)
    }

    override fun onGroupMemberDeleteRequestClick(id: UserId) {
        communication.onGroupMemberDeleteRequested(id)
    }

    override fun onGroupCodeGenerateRequestClick(id: GroupId) {
        if (isGettingCodeProgress.value) return

        componentScope.safeLaunch(errorHandler) {
            withProgress(isGettingCodeProgress) {
                val groupInvitationData = repositoryGroupInvitationDataRepository.getGroupInvitationData(id)
                groupInvitationDataState.value = groupInvitationData
            }
        }
    }
}
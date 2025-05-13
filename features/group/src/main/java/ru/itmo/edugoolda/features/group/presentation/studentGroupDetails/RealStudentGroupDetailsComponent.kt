package ru.itmo.edugoolda.features.group.presentation.studentGroupDetails

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.MutableStateFlow
import me.aartikov.replica.algebra.normal.withKey
import ru.itmo.edugoolda.core.error_handling.ErrorHandler
import ru.itmo.edugoolda.core.error_handling.safeLaunch
import ru.itmo.edugoolda.core.utils.componentScope
import ru.itmo.edugoolda.core.utils.observe
import ru.itmo.edugoolda.core.utils.withProgress
import ru.itmo.edugoolda.data.group.group_info.api.GroupFullInfoRepository
import ru.itmo.edugoolda.data.group.group_list.api.GroupId
import ru.itmo.edugoolda.data.group.group_students_list.api.GroupStudentsRepository

class RealStudentGroupDetailsComponent(
    private val groupId: GroupId,
    componentContext: ComponentContext,
    private val communication: StudentGroupDetailsComponent.Communication,
    private val errorHandler: ErrorHandler,
    private val repositoryGroupOfStudents: GroupStudentsRepository,
    private val repositoryGroupFullInfo: GroupFullInfoRepository,
) : StudentGroupDetailsComponent, ComponentContext by componentContext {
    private val groupInfoReplica =
        repositoryGroupFullInfo.groupInfoReplica.withKey(groupId)
    override val groupInfoState = groupInfoReplica.observe(this, errorHandler)
    override val isQuitingGroupProgress = MutableStateFlow(false)

    override fun onRefresh() {
        groupInfoReplica.refresh()
    }

    override fun onRetryClick() {
        groupInfoReplica.revalidate()
    }

    override fun onReturnBackRequestClick() {
        communication.onReturnBackRequested()
    }

    override fun onGroupQuitRequestClick() {
        if (isQuitingGroupProgress.value) return

        componentScope.safeLaunch(errorHandler) {
            withProgress(isQuitingGroupProgress) {
                repositoryGroupOfStudents.leaveFromGroup(groupId)
            }
        }
    }
}
package ru.itmo.edugoolda.features.group.presentation.studentGroupDetails

import com.arkivanov.decompose.ComponentContext
import dev.icerock.moko.resources.desc.strResDesc
import kotlinx.coroutines.flow.MutableStateFlow
import me.aartikov.replica.algebra.normal.withKey
import ru.itmo.edugoolda.core.dialog.standard.DialogButton
import ru.itmo.edugoolda.core.dialog.standard.StandardDialogControl
import ru.itmo.edugoolda.core.dialog.standard.StandardDialogData
import ru.itmo.edugoolda.core.dialog.standard.standardDialogControl
import ru.itmo.edugoolda.core.error_handling.ErrorHandler
import ru.itmo.edugoolda.core.error_handling.safeLaunch
import ru.itmo.edugoolda.core.utils.componentScope
import ru.itmo.edugoolda.core.utils.observe
import ru.itmo.edugoolda.core.utils.withProgress
import ru.itmo.edugoolda.data.group.group_info.api.GroupFullInfoRepository
import ru.itmo.edugoolda.data.group.group_list.api.GroupId
import ru.itmo.edugoolda.data.group.group_students_list.api.GroupStudentsRepository
import ru.itmo.edugoolda.features.group.R

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
    override val dialogQuit = standardDialogControl("quit")


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
            communication.onGroupQuitRequested()
        }
    }

    override fun onDialogQuitRequest() {
        dialogQuit.show(
            StandardDialogData(
                title = R.string.quit_group_title.strResDesc(),
                message = R.string.quit_group_message.strResDesc(),
                confirmButton = DialogButton(
                    text = R.string.quit_group_confirm.strResDesc(),
                    action = {
                        onGroupQuitRequestClick()
                        dialogQuit.dismiss()
                    }
                ),
                dismissButton = DialogButton(
                    text = R.string.quit_group_confirm.strResDesc(),
                    action = {
                        dialogQuit.dismiss()
                    }
                ),
                dismissableByUser = true
            )
        )
    }
}
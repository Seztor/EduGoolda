package ru.itmo.edugoolda.features.group.presentation.studentGroupDetails

import kotlinx.coroutines.flow.StateFlow
import ru.itmo.edugoolda.core.dialog.standard.StandardDialogControl
import ru.itmo.edugoolda.core.utils.LoadableState
import ru.itmo.edugoolda.data.group.group_info.api.GroupFullInfo
import ru.itmo.edugoolda.data.group.group_list.api.GroupId

interface StudentGroupDetailsComponent {
    val groupInfoState: StateFlow<LoadableState<GroupFullInfo>>
    val isQuitingGroupProgress: StateFlow<Boolean>
    val dialogQuit : StandardDialogControl

    fun onRefresh()
    fun onRetryClick()
    fun onReturnBackRequestClick()
    fun onGroupQuitRequestClick()
    fun onDialogQuitRequest()

    interface Communication {
        fun onReturnBackRequested()
        fun onGroupQuitRequested()
    }
}
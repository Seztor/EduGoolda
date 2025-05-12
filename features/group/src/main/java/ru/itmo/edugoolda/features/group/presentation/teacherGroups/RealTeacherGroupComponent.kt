package ru.itmo.edugoolda.features.group.presentation.teacherGroups

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.MutableStateFlow
import me.aartikov.replica.algebra.paged.withKey
import ru.itmo.edugoolda.core.error_handling.ErrorHandler
import ru.itmo.edugoolda.core.error_handling.safeLaunch
import ru.itmo.edugoolda.core.utils.componentScope
import ru.itmo.edugoolda.core.utils.observe
import ru.itmo.edugoolda.core.utils.withProgress
import ru.itmo.edugoolda.data.group.group_list.api.GroupId
import ru.itmo.edugoolda.data.group.group_list.api.GroupListRepository
import ru.mobileup.kmm_form_validation.control.InputControl

class RealTeacherGroupComponent(
    componentContext: ComponentContext,
    private val communication: TeacherGroupComponent.Communication,
    private val errorHandler: ErrorHandler,
    private val groupListRepository: GroupListRepository,
) : TeacherGroupComponent, ComponentContext by componentContext {
    override val groupSearchInputControl = InputControl(componentScope)
    private val teacherGroupReplica = groupListRepository.groupInfoListReplica.withKey(groupSearchInputControl.text)
    override val teacherGroupState = teacherGroupReplica.observe(this, errorHandler)

    override val isChangingFavouriteStatus = MutableStateFlow(false)

    override fun onRefresh() {
        teacherGroupReplica.refresh()
    }

    override fun onRetryClick() {
        teacherGroupReplica.revalidate()
    }

    override fun onLoadNext() {
        teacherGroupReplica.loadNext()
    }

    override fun onGroupDetailRequestClick(id: GroupId) {
        communication.onGroupDetailsRequested(id)
    }

    override fun onGroupChangeFavouriteStatusRequestClick(id: GroupId, isFavourite: Boolean) {
        if (isChangingFavouriteStatus.value) return

        componentScope.safeLaunch(errorHandler) {
            withProgress(isChangingFavouriteStatus) {
                groupListRepository.changeFavouriteStatus(id, isFavourite)
            }
        }
    }
}
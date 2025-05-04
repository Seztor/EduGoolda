package ru.itmo.edugoolda.features.group.presentation.teacherGroups

import com.arkivanov.decompose.ComponentContext
import ru.itmo.edugoolda.core.error_handling.ErrorHandler
import ru.itmo.edugoolda.core.utils.componentScope
import ru.itmo.edugoolda.core.utils.observe
import ru.itmo.edugoolda.data.group.groupList.api.GroupId
import ru.itmo.edugoolda.data.group.groupList.api.GroupRepository
import ru.mobileup.kmm_form_validation.control.InputControl

class RealTeacherGroupComponent(
    componentContext: ComponentContext,
    private val communication: TeacherGroupComponent.Communication,
    private val errorHandler: ErrorHandler,
    private val groupRepository: GroupRepository,
) : TeacherGroupComponent, ComponentContext by componentContext {
    override val groupSearchInputControl = InputControl(componentScope)
    private val teacherGroupReplica = groupRepository.groupListReplica
    override val teacherGroupState = teacherGroupReplica.observe(this, errorHandler)

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

    override fun onGroupChangeFavouriteStatusRequestClick(id: GroupId) {
        communication.onGroupChangeFavouriteStatusRequested(id)
    }
}
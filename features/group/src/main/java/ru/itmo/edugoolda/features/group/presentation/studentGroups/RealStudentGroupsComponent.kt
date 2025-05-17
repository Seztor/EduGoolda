package ru.itmo.edugoolda.features.group.presentation.studentGroups

import com.arkivanov.decompose.ComponentContext
import me.aartikov.replica.algebra.paged.withKey
import ru.itmo.edugoolda.core.error_handling.ErrorHandler
import ru.itmo.edugoolda.core.utils.componentScope
import ru.itmo.edugoolda.core.utils.observe
import ru.itmo.edugoolda.data.group.group_list.api.GroupId
import ru.itmo.edugoolda.data.group.group_list.api.GroupListRepository
import ru.mobileup.kmm_form_validation.control.InputControl

class RealStudentGroupsComponent(
    componentContext: ComponentContext,
    private val communication: StudentGroupsComponent.Communication,
    private val errorHandler: ErrorHandler,
    private val groupListRepository: GroupListRepository,
) : StudentGroupsComponent, ComponentContext by componentContext {
    override val groupSearchInputControl = InputControl(componentScope)
    private val studentGroupReplica = groupListRepository.groupInfoListReplica.withKey(groupSearchInputControl.text)
    override val studentGroupState = studentGroupReplica.observe(this, errorHandler)

    override fun onRefresh() {
        studentGroupReplica.refresh()
    }

    override fun onRetryClick() {
        studentGroupReplica.revalidate()
    }

    override fun onLoadNext() {
        studentGroupReplica.loadNext()
    }

    override fun onGroupDetailsRequestClick(id: GroupId) {
        communication.onGroupDetailsRequested(id)
    }

    override fun onGroupAddRequestClick() {
        communication.onGroupAddRequested()
    }
}
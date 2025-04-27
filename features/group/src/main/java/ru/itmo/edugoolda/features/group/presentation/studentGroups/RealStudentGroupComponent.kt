package ru.itmo.edugoolda.features.group.presentation.studentGroups

import com.arkivanov.decompose.ComponentContext
import ru.itmo.edugoolda.core.error_handling.ErrorHandler
import ru.itmo.edugoolda.core.error_handling.safeLaunch
import ru.itmo.edugoolda.core.utils.componentScope
import ru.itmo.edugoolda.core.utils.observe
import ru.itmo.edugoolda.data.group.studentGroups.api.StudentGroupRepository
import ru.mobileup.kmm_form_validation.control.InputControl

class RealStudentGroupComponent(
    componentContext: ComponentContext,
    private val errorHandler: ErrorHandler,
    private val studentGroupRepository: StudentGroupRepository,
) : StudentGroupComponent, ComponentContext by componentContext {
    override val groupSearchInputControl = InputControl(componentScope)
    private val studentGroupReplica = studentGroupRepository.studentGroupReplica
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

    override fun onGroupDetailRequestClick(id: String) {
        componentScope.safeLaunch(errorHandler) {
            studentGroupRepository.onGroupRequest(id)
        }
    }
}
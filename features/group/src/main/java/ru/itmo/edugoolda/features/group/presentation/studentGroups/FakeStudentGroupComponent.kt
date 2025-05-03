package ru.itmo.edugoolda.features.group.presentation.studentGroups

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import ru.itmo.edugoolda.core.utils.PagedState
import ru.itmo.edugoolda.data.group.studentGroups.api.StudentGroupId
import ru.itmo.edugoolda.data.group.studentGroups.api.StudentGroups
import ru.mobileup.kmm_form_validation.control.InputControl

class FakeStudentGroupComponent() : StudentGroupComponent {
    override val groupSearchInputControl = InputControl(GlobalScope)
    override val studentGroupState = MutableStateFlow(PagedState(data = StudentGroups.MOCK))

    override fun onRefresh() {
        TODO("Not yet implemented")
    }

    override fun onRetryClick() {
        TODO("Not yet implemented")
    }

    override fun onLoadNext() {
        TODO("Not yet implemented")
    }

    override fun onGroupDetailRequestClick(id: StudentGroupId) {
        TODO("Not yet implemented")
    }
}
package ru.itmo.edugoolda.features.group.presentation.studentGroups

import kotlinx.coroutines.flow.StateFlow
import ru.mobileup.kmm_form_validation.control.InputControl

interface StudentGroupComponent {
    val groupSearchInputControl: InputControl
//    val groupsList: StateFlow<List<>>

    fun onGroupClick(id: String)

    interface Communication {
        fun onGroupClicked(id: String)
    }
}
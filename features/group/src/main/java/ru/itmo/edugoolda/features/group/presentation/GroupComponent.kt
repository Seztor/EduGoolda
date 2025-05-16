package ru.itmo.edugoolda.features.group.presentation

import com.arkivanov.decompose.router.stack.ChildStack
import kotlinx.coroutines.flow.StateFlow
import ru.itmo.edugoolda.features.group.presentation.addGroup.GroupAddComponent
import ru.itmo.edugoolda.features.group.presentation.studentGroupDetails.StudentGroupDetailsComponent
import ru.itmo.edugoolda.features.group.presentation.studentGroups.StudentGroupComponent
import ru.itmo.edugoolda.features.group.presentation.teacherGroupDetails.TeacherGroupDetailsComponent
import ru.itmo.edugoolda.features.group.presentation.teacherGroups.TeacherGroupComponent
import ru.itmo.edugoolda.features.group.presentation.createGroup.GroupCreateComponent

interface GroupComponent {

    val childStack: StateFlow<ChildStack<*, Child>>

    sealed interface Child {
        class CreateGroup(val instance: GroupCreateComponent) : Child
        class AddGroup(val instance: GroupAddComponent) : Child
        class StudentGroupsList(val instance: StudentGroupComponent) : Child
        class StudentGroupDetails(val instance: StudentGroupDetailsComponent) : Child
        class TeacherGroupsList(val instance: TeacherGroupComponent) : Child
        class TeacherGroupDetails(val instance: TeacherGroupDetailsComponent) : Child
    }
}
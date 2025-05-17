package ru.itmo.edugoolda.features.group.presentation

import com.arkivanov.decompose.router.stack.ChildStack
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.Serializable
import ru.itmo.edugoolda.data.group.group_list.api.GroupId
import ru.itmo.edugoolda.features.group.presentation.addGroup.GroupAddComponent
import ru.itmo.edugoolda.features.group.presentation.createGroup.GroupCreateComponent
import ru.itmo.edugoolda.features.group.presentation.studentGroupDetails.StudentGroupDetailsComponent
import ru.itmo.edugoolda.features.group.presentation.studentGroups.StudentGroupsComponent
import ru.itmo.edugoolda.features.group.presentation.teacherGroupDetails.TeacherGroupDetailsComponent
import ru.itmo.edugoolda.features.group.presentation.teacherGroups.TeacherGroupsComponent

interface GroupComponent {

    val childStack: StateFlow<ChildStack<*, Child>>

    @Serializable
    sealed interface InitialConfiguration {
        @Serializable
        data object CreateGroup : InitialConfiguration

        @Serializable
        data object AddGroup : InitialConfiguration

        @Serializable
        data object StudentGroupsList : InitialConfiguration

        @Serializable
        data class StudentGroupDetails(val groupId: GroupId) : InitialConfiguration

        @Serializable
        data object TeacherGroupsList : InitialConfiguration

        @Serializable
        data class TeacherGroupDetails(val groupId: GroupId) : InitialConfiguration
    }

    sealed interface Child {
        class CreateGroup(val instance: GroupCreateComponent) : Child
        class AddGroup(val instance: GroupAddComponent) : Child
        class StudentGroupsList(val instance: StudentGroupsComponent) : Child
        class StudentGroupDetails(val instance: StudentGroupDetailsComponent) : Child
        class TeacherGroupsList(val instance: TeacherGroupsComponent) : Child
        class TeacherGroupDetails(val instance: TeacherGroupDetailsComponent) : Child
    }

    interface Communication {
        fun onCancel()
    }
}
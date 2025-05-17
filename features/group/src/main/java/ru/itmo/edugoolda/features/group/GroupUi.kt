package ru.itmo.edugoolda.features.group

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import ru.itmo.edugoolda.features.group.presentation.GroupComponent
import ru.itmo.edugoolda.features.group.presentation.addGroup.GroupAddUi
import ru.itmo.edugoolda.features.group.presentation.createGroup.GroupCreateUi
import ru.itmo.edugoolda.features.group.presentation.studentGroupDetails.StudentGroupDetailsUi
import ru.itmo.edugoolda.features.group.presentation.studentGroups.StudentGroupsUi
import ru.itmo.edugoolda.features.group.presentation.teacherGroupDetails.TeacherGroupDetailsUi
import ru.itmo.edugoolda.features.group.presentation.teacherGroups.TeacherGroupsUi

@Composable
fun GroupUi(
    component: GroupComponent,
    modifier: Modifier = Modifier
) {
    val stack by component.childStack.collectAsState()

    Children(stack, modifier) {
        when (val instance = it.instance) {
            is GroupComponent.Child.AddGroup -> GroupAddUi(instance.instance)
            is GroupComponent.Child.CreateGroup -> GroupCreateUi(instance.instance)
            is GroupComponent.Child.StudentGroupDetails -> StudentGroupDetailsUi(instance.instance)
            is GroupComponent.Child.StudentGroupsList -> StudentGroupsUi(instance.instance)
            is GroupComponent.Child.TeacherGroupDetails -> TeacherGroupDetailsUi(instance.instance)
            is GroupComponent.Child.TeacherGroupsList -> TeacherGroupsUi(instance.instance)
        }
    }
}
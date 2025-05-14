package ru.itmo.edugoolda.features.group

import com.arkivanov.decompose.ComponentContext
import org.koin.core.component.get
import ru.itmo.edugoolda.core.ComponentFactory
import ru.itmo.edugoolda.data.group.group_list.api.GroupId
import ru.itmo.edugoolda.features.group.presentation.createGroup.GroupCreateComponent
import ru.itmo.edugoolda.features.group.presentation.createGroup.RealGroupCreateComponent
import ru.itmo.edugoolda.features.group.presentation.studentGroups.RealStudentGroupComponent
import ru.itmo.edugoolda.features.group.presentation.studentGroups.StudentGroupComponent
import ru.itmo.edugoolda.features.group.presentation.studentGroupDetails.RealStudentGroupDetailsComponent
import ru.itmo.edugoolda.features.group.presentation.studentGroupDetails.StudentGroupDetailsComponent
import ru.itmo.edugoolda.features.group.presentation.teacherGroupDetails.RealTeacherGroupDetailsComponent
import ru.itmo.edugoolda.features.group.presentation.teacherGroupDetails.TeacherGroupDetailsComponent
import ru.itmo.edugoolda.features.group.presentation.teacherGroups.RealTeacherGroupComponent
import ru.itmo.edugoolda.features.group.presentation.teacherGroups.TeacherGroupComponent

fun ComponentFactory.createGroupCreateComponent(
    componentContext: ComponentContext,
    communication: GroupCreateComponent.Communication,
): RealGroupCreateComponent {
    return RealGroupCreateComponent(componentContext, communication, get(), get())
}

fun ComponentFactory.createStudentGroupComponent(
    componentContext: ComponentContext,
    communication: StudentGroupComponent.Communication,
): RealStudentGroupComponent {
    return RealStudentGroupComponent(componentContext, communication, get(), get())
}

fun ComponentFactory.createTeacherGroupComponent(
    componentContext: ComponentContext,
    communication: TeacherGroupComponent.Communication,
): RealTeacherGroupComponent {
    return RealTeacherGroupComponent(componentContext, communication, get(), get())
}

fun ComponentFactory.createTeacherGroupDetailsComponent(
    groupId: GroupId,
    componentContext: ComponentContext,
    communication: TeacherGroupDetailsComponent.Communication,
    ): RealTeacherGroupDetailsComponent {
    return RealTeacherGroupDetailsComponent(groupId, componentContext, communication, get(), get(), get(), get(), get())
}

fun ComponentFactory.createStudentGroupDetailsComponent(
    groupId: GroupId,
    componentContext: ComponentContext,
    communication: StudentGroupDetailsComponent.Communication,
): RealStudentGroupDetailsComponent {
    return RealStudentGroupDetailsComponent(groupId, componentContext, communication, get(), get(), get())
}
package ru.itmo.edugoolda.features.group

import com.arkivanov.decompose.ComponentContext
import org.koin.core.component.get
import ru.itmo.edugoolda.core.ComponentFactory
import ru.itmo.edugoolda.features.group.presentation.create.GroupCreateComponent
import ru.itmo.edugoolda.features.group.presentation.create.RealGroupCreateComponent
import ru.itmo.edugoolda.features.group.presentation.studentGroups.RealStudentGroupComponent
import ru.itmo.edugoolda.features.group.presentation.studentGroups.StudentGroupComponent

fun ComponentFactory.createGroupCreateComponent(
    componentContext: ComponentContext,
    communication: GroupCreateComponent.Communication,
): GroupCreateComponent {
    return RealGroupCreateComponent(componentContext, communication, get(), get())
}

fun ComponentFactory.createStudentGroupComponent(
    componentContext: ComponentContext,
    communication: StudentGroupComponent.Communication,
): RealStudentGroupComponent {
    return RealStudentGroupComponent(componentContext, communication, get(), get())
}
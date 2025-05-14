package ru.itmo.edugoolda.features.group.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import kotlinx.serialization.Serializable
import ru.itmo.edugoolda.core.ComponentFactory
import ru.itmo.edugoolda.core.utils.getChild
import ru.itmo.edugoolda.core.utils.safePush
import ru.itmo.edugoolda.core.utils.toStateFlow
import ru.itmo.edugoolda.data.group.group_list.api.GroupId
import ru.itmo.edugoolda.features.group.createGroupAddComponent
import ru.itmo.edugoolda.features.group.createGroupCreateComponent
import ru.itmo.edugoolda.features.group.createStudentGroupComponent
import ru.itmo.edugoolda.features.group.createStudentGroupDetailsComponent
import ru.itmo.edugoolda.features.group.createTeacherGroupComponent
import ru.itmo.edugoolda.features.group.createTeacherGroupDetailsComponent
import ru.itmo.edugoolda.features.group.presentation.addGroup.GroupAddComponent
import ru.itmo.edugoolda.features.group.presentation.create.GroupCreateComponent
import ru.itmo.edugoolda.features.group.presentation.studentGroupDetails.StudentGroupDetailsComponent
import ru.itmo.edugoolda.features.group.presentation.studentGroups.StudentGroupComponent
import ru.itmo.edugoolda.features.group.presentation.teacherGroupDetails.TeacherGroupDetailsComponent
import ru.itmo.edugoolda.features.group.presentation.teacherGroups.TeacherGroupComponent

class RealGroupComponent(
    componentContext: ComponentContext,
    private val componentFactory: ComponentFactory,
) : GroupComponent, ComponentContext by componentContext {
    private val navigation = StackNavigation<Config>()

    override val childStack = childStack(
        source = navigation,
        initialConfiguration = Config.CreateGroup,
        serializer = Config.serializer(),
        handleBackButton = true,
        childFactory = ::createChild
    ).toStateFlow(lifecycle)

    private inner class TeacherCommunicationResolver : GroupCreateComponent.Communication,
        TeacherGroupComponent.Communication, TeacherGroupDetailsComponent.Communication {
        override fun onGroupCreated() {
            navigation.pop()
        }

        override fun onCancel() {
            navigation.pop()
        }

        override fun onGroupDetailsRequested(id: GroupId) {
            navigation.safePush(Config.TeacherGroupDetails)
        }

        override fun onReturnBackRequested() {
            navigation.pop()
        }

        override fun onGroupDeleteRequested() {
            navigation.pop()
        }
    }

    private inner class StudentCommunicationResolver : StudentGroupComponent.Communication, StudentGroupDetailsComponent.Communication, GroupAddComponent.Communication {
        override fun onGroupDetailsRequested(id: GroupId) {
            navigation.safePush(Config.StudentGroupDetails)
        }

        override fun onGroupAddRequested() {
            navigation.safePush(Config.AddGroup)
        }

        override fun onReturnBackRequested() {
            navigation.pop()
        }

        override fun onGroupQuitRequested() {
            navigation.pop()
        }

        override fun onGroupAdded() {
            navigation.pop()
        }

        override fun onCancel() {
            navigation.pop()
        }
    }

    private fun createChild(
        config: Config,
        componentContext: ComponentContext,
    ): GroupComponent.Child = when (config) {
        Config.CreateGroup -> GroupComponent.Child.CreateGroup(
            componentFactory.createGroupCreateComponent(
                componentContext,
                TeacherCommunicationResolver()
            )
        )

        is Config.TeacherGroupDetails -> GroupComponent.Child.TeacherGroupDetails(
            componentFactory.createTeacherGroupDetailsComponent(
                groupId = config.id,
                componentContext,
                TeacherCommunicationResolver()
            )
        )

        Config.TeacherGroupsList -> GroupComponent.Child.TeacherGroupsList(
            componentFactory.createTeacherGroupComponent(
                componentContext,
                TeacherCommunicationResolver()
            )
        )

        Config.AddGroup -> GroupComponent.Child.AddGroup(
            componentFactory.createGroupAddComponent(
                componentContext,
                StudentCommunicationResolver()
            )
        )

        is Config.StudentGroupDetails -> GroupComponent.Child.StudentGroupDetails(
            componentFactory.createStudentGroupDetailsComponent(
                groupId = config.id,
                componentContext,
                StudentCommunicationResolver()
            )
        )

        Config.StudentGroupsList -> GroupComponent.Child.StudentGroupsList(
            componentFactory.createStudentGroupComponent(
                componentContext,
                StudentCommunicationResolver()
            )
        )
    }

    @Serializable
    sealed interface Config {
        @Serializable
        data object CreateGroup : Config

        @Serializable
        data class TeacherGroupDetails(val id: GroupId) : Config

        @Serializable
        data object TeacherGroupsList : Config

        @Serializable
        data object AddGroup : Config

        @Serializable
        data class StudentGroupDetails(val id: GroupId) : Config

        @Serializable
        data object StudentGroupsList : Config
    }
}
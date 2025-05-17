package ru.itmo.edugoolda.features.root.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.replaceAll
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import ru.itmo.edugoolda.core.ComponentFactory
import ru.itmo.edugoolda.core.createMessageComponent
import ru.itmo.edugoolda.core.utils.componentScope
import ru.itmo.edugoolda.core.utils.safePush
import ru.itmo.edugoolda.core.utils.toStateFlow
import ru.itmo.edugoolda.data.group.group_list.api.GroupId
import ru.itmo.edugoolda.data.solutions.api.SolutionId
import ru.itmo.edugoolda.data.user.api.UserInfoStore
import ru.itmo.edugoolda.data.user.api.UserRole
import ru.itmo.edugoolda.features.auth.presentation.auth.AuthComponent
import ru.itmo.edugoolda.features.group.createGroupComponent
import ru.itmo.edugoolda.features.group.presentation.GroupComponent
import ru.itmo.edugoolda.features.join_requests.createJoinRequestsComponent
import ru.itmo.edugoolda.features.lesson.LessonsComponent
import ru.itmo.edugoolda.features.lesson.createLessonsComponent
import ru.itmo.edugoolda.features.main.createMainStudentComponent
import ru.itmo.edugoolda.features.main.createMainTeacherComponent
import ru.itmo.edugoolda.features.main.presentation.student.MainStudentComponent
import ru.itmo.edugoolda.features.main.presentation.teacher.MainTeacherComponent
import ru.itmo.edugoolda.features.root.createAuthComponent

class RealRootComponent(
    componentContext: ComponentContext,
    private val componentFactory: ComponentFactory,
    userInfoStore: UserInfoStore
) : ComponentContext by componentContext, RootComponent {

    private val navigation = StackNavigation<Config>()

    override val childStack = childStack(
        source = navigation,
        initialConfiguration = Config.Auth,
        serializer = Config.serializer(),
        handleBackButton = true,
        childFactory = ::createChild
    ).toStateFlow(lifecycle)

    override val messageComponent = componentFactory.createMessageComponent(
        childContext(key = "message")
    )

    private fun createChild(
        config: Config,
        componentContext: ComponentContext
    ): RootComponent.Child = when (config) {
        Config.Auth -> RootComponent.Child.Auth(
            componentFactory.createAuthComponent(
                componentContext,
                GeneralCommunicationResolver()
            )
        )

        Config.MainStudent -> RootComponent.Child.StudentMain(
            componentFactory.createMainStudentComponent(
                componentContext,
                StudentCommunicationResolver()
            )
        )

        Config.MainTeacher -> RootComponent.Child.TeacherMain(
            componentFactory.createMainTeacherComponent(
                componentContext,
                TeacherCommunicationResolver()
            )
        )

        is Config.Group -> RootComponent.Child.Group(
            componentFactory.createGroupComponent(
                componentContext,
                GeneralCommunicationResolver(),
                config.configuration,
            )
        )

        Config.JoinRequests -> RootComponent.Child.JoinRequests(
            componentFactory.createJoinRequestsComponent(componentContext)
        )

        is Config.Lessons -> RootComponent.Child.Lessons(
            componentFactory.createLessonsComponent(
                componentContext,
                config.configuration,
                GeneralCommunicationResolver()
            )
        )
    }

    private val userRole =
        userInfoStore.getUserRole().stateIn(componentScope, SharingStarted.Eagerly, null)

    private inner class GeneralCommunicationResolver :
        AuthComponent.Communication,
        LessonsComponent.Communication,
        GroupComponent.Communication {
        override fun onAuthEnded() {
            componentScope.launch {
                val role = userRole.filterNotNull().first()
                navigation.replaceAll(
                    when (role) {
                        UserRole.Teacher -> Config.MainTeacher
                        UserRole.Student -> Config.MainStudent
                    }
                )
            }
        }

        override fun onCancel() {
            navigation.pop()
        }
    }

    private inner class TeacherCommunicationResolver : MainTeacherComponent.Communication {
        override fun onGroupDetailsRequested(id: GroupId) {
            navigation.safePush(
                Config.Group(
                    GroupComponent.InitialConfiguration.TeacherGroupDetails(id)
                )
            )
        }

        override fun onSolutionDetailsRequested(solutionId: SolutionId) {
            navigation.safePush(
                Config.Lessons(
                    LessonsComponent.InitialConfiguration.TeacherSolutionDetails(solutionId)
                )
            )
        }

        override fun onAllSolutionsRequested() {
            navigation.safePush(
                Config.Lessons(
                    LessonsComponent.InitialConfiguration.TeacherSolutionsInfoList
                )
            )
        }

        override fun onAllJoinRequestsRequested() {
            navigation.safePush(Config.JoinRequests)
        }

        override fun createLessonRequested() {
            navigation.safePush(
                Config.Lessons(
                    LessonsComponent.InitialConfiguration.TeacherLessonCreate
                )
            )
        }

        override fun createGroupRequested() {
            navigation.safePush(
                Config.Group(
                    GroupComponent.InitialConfiguration.CreateGroup
                )
            )
        }
    }

    private inner class StudentCommunicationResolver : MainStudentComponent.Communication {
        override fun onGroupDetailsRequested(id: GroupId) {
            navigation.safePush(
                Config.Group(
                    GroupComponent.InitialConfiguration.StudentGroupDetails(id)
                )
            )
        }

        override fun onGroupAddRequested() {
            navigation.safePush(
                Config.Group(
                    GroupComponent.InitialConfiguration.AddGroup
                )
            )
        }

        override fun onAllJoinRequestsRequested() {
            navigation.safePush(Config.JoinRequests)
        }

        override fun onEditLessonRequested() {
            // TODO: in future
        }
    }

    @Serializable
    sealed interface Config {
        @Serializable
        data object Auth : Config

        @Serializable
        data object MainStudent : Config

        @Serializable
        data object MainTeacher : Config

        @Serializable
        data class Group(
            val configuration: GroupComponent.InitialConfiguration
        ) : Config

        @Serializable
        data class Lessons(
            val configuration: LessonsComponent.InitialConfiguration
        ) : Config

        @Serializable
        data object JoinRequests : Config
    }
}

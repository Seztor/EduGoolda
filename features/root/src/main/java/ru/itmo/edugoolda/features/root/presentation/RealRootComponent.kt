package ru.itmo.edugoolda.features.root.presentation

import android.util.Log
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import kotlinx.serialization.Serializable
import ru.itmo.edugoolda.core.ComponentFactory
import ru.itmo.edugoolda.core.createMessageComponent
import ru.itmo.edugoolda.core.utils.safePush
import ru.itmo.edugoolda.core.utils.toStateFlow
import ru.itmo.edugoolda.data.group.group_list.api.GroupId
import ru.itmo.edugoolda.features.auth.presentation.auth.AuthComponent
import ru.itmo.edugoolda.features.group.createTeacherGroupComponent
import ru.itmo.edugoolda.features.group.presentation.teacherGroups.TeacherGroupComponent
import ru.itmo.edugoolda.features.root.createAuthComponent

class RealRootComponent(
    componentContext: ComponentContext,
    private val componentFactory: ComponentFactory
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
                CommunicationResolver()
            )
        )
        Config.GroupList -> RootComponent.Child.GroupList(
            componentFactory.createTeacherGroupComponent(
                componentContext,
                CommunicationResolver()
            )
        )
    }

    private inner class CommunicationResolver : AuthComponent.Communication, TeacherGroupComponent.Communication {
        override fun onAuthEnded() {
            navigation.safePush(Config.GroupList)
            Log.d("123456","12345")
        }

        override fun onGroupDetailsRequested(id: GroupId) {
            TODO("Not yet implemented")
        }

        override fun onGroupChangeFavouriteStatusRequested(id: GroupId) {
            TODO("Not yet implemented")
        }
    }

    @Serializable
    sealed interface Config {
        @Serializable
        data object Auth : Config
        @Serializable
        data object GroupList : Config
    }
}

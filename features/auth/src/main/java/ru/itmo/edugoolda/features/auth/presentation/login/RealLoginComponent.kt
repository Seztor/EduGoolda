package ru.itmo.edugoolda.features.auth.presentation.login

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import kotlinx.serialization.Serializable
import ru.itmo.edugoolda.core.ComponentFactory
import ru.itmo.edugoolda.core.utils.toStateFlow
import ru.itmo.edugoolda.features.auth.createLoginCreateComponent
import ru.itmo.edugoolda.features.auth.presentation.create.login.LoginCreateComponent

class RealLoginComponent(
    componentContext: ComponentContext,
    private val componentFactory: ComponentFactory
) : LoginComponent, ComponentContext by componentContext {
    private val navigation = StackNavigation<Config>()

    override val childStack = childStack(
        source = navigation,
        initialConfiguration = Config.Login,
        serializer = Config.serializer(),
        handleBackButton = true,
        childFactory = ::createChild
    ).toStateFlow(lifecycle)

    private inner class CommunicationResolver : LoginCreateComponent.Communication {
        override fun onLoggedIn() {
            TODO("Not yet implemented")
        }

        override fun onNavigateToRegister() {
            TODO("Not yet implemented")
        }
    }

    private fun createChild(
        config: Config,
        componentContext: ComponentContext
    ): LoginComponent.Child = when (config) {
        Config.Login -> LoginComponent.Child.Login(
            componentFactory.createLoginCreateComponent(
                componentContext,
                CommunicationResolver()
            )
        )
    }

    @Serializable
    sealed interface Config {
        @Serializable
        data object Login : Config
    }
}
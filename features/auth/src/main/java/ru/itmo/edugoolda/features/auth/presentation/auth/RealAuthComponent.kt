package ru.itmo.edugoolda.features.auth.presentation.auth

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import kotlinx.serialization.Serializable
import ru.itmo.edugoolda.core.ComponentFactory
import ru.itmo.edugoolda.core.utils.safePush
import ru.itmo.edugoolda.core.utils.toStateFlow
import ru.itmo.edugoolda.features.auth.createLoginComponent
import ru.itmo.edugoolda.features.auth.createRegisterComponent
import ru.itmo.edugoolda.features.auth.presentation.login.LoginComponent
import ru.itmo.edugoolda.features.auth.presentation.register.RegisterComponent

class RealAuthComponent(
    componentContext: ComponentContext,
    private val componentFactory: ComponentFactory
) : AuthComponent, ComponentContext by componentContext {
    private val navigation = StackNavigation<Config>()

    override val childStack = childStack(
        source = navigation,
        initialConfiguration = Config.Login,
        serializer = Config.serializer(),
        handleBackButton = true,
        childFactory = ::createChild
    ).toStateFlow(lifecycle)

    private inner class CommunicationResolver : LoginComponent.Communication, RegisterComponent.Communication {
        override fun onLoggedIn() {
            TODO("Not yet implemented")
        }

        override fun onRegisterRequest() {
            navigation.safePush(Config.Register)
        }

        override fun onRegistered() {
            TODO("Not yet implemented")
        }

        override fun onNavigateToMainMenu() {
            TODO("Not yet implemented")
        }
    }

    private fun createChild(
        config: Config,
        componentContext: ComponentContext
    ): AuthComponent.Child = when (config) {
        Config.Login -> AuthComponent.Child.Login(
            componentFactory.createLoginComponent(
                componentContext,
                CommunicationResolver()
            )
        )
        Config.Register -> AuthComponent.Child.Register(
            componentFactory.createRegisterComponent(
                componentContext,
                CommunicationResolver()
            )
        )
    }

    @Serializable
    sealed interface Config {
        @Serializable
        data object Login : Config
        @Serializable
        data object Register : Config
    }
}
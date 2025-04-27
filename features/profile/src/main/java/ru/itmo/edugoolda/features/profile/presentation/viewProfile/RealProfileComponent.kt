package ru.itmo.edugoolda.features.profile.presentation.viewProfile

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.StateFlow
import me.aartikov.replica.algebra.normal.withKey
import ru.itmo.edugoolda.core.error_handling.ErrorHandler
import ru.itmo.edugoolda.core.utils.LoadableState
import ru.itmo.edugoolda.data.user.api.Profile
import ru.itmo.edugoolda.data.user.api.UserRepository
import ru.itmo.edugoolda.data.user.api.UserRole
import ru.itmo.edugoolda.core.utils.observe
import ru.itmo.edugoolda.data.user.api.UserId

class RealProfileComponent(
    componentContext: ComponentContext,
    repository: UserRepository,
    errorHandler: ErrorHandler
) : ProfileComponent, ComponentContext by componentContext {
    private val profileReplica = repository.profileReplica.withKey(UserId("text"))
    override val profileState = profileReplica.observe(this, errorHandler)


    override fun onRefresh() {
        profileReplica.refresh()
    }

    override fun onRetryClick() {
        profileReplica.revalidate()
    }
}
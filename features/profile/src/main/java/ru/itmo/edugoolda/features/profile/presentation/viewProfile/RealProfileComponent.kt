package ru.itmo.edugoolda.features.profile.presentation.viewProfile

import com.arkivanov.decompose.ComponentContext
import me.aartikov.replica.algebra.normal.withKey
import ru.itmo.edugoolda.core.error_handling.ErrorHandler
import ru.itmo.edugoolda.core.utils.observe
import ru.itmo.edugoolda.data.user.api.UserId
import ru.itmo.edugoolda.data.user.api.UserRepository

class RealProfileComponent(
    userId: UserId?,
    componentContext: ComponentContext,
    repository: UserRepository,
    errorHandler: ErrorHandler,
) : ProfileComponent, ComponentContext by componentContext {

    private val profileReplica = when (userId) {
        null -> repository.currentProfileReplica
        else -> repository.profileReplica.withKey(userId)
    }

    override val profileState = profileReplica.observe(this, errorHandler)

    override fun onRefresh() {
        profileReplica.refresh()
    }

    override fun onRetryClick() {
        profileReplica.revalidate()
    }
}
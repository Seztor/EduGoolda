package ru.itmo.edugoolda.features.profile.presentation.viewProfile

import kotlinx.coroutines.flow.StateFlow
import ru.itmo.edugoolda.core.utils.LoadableState
import ru.itmo.edugoolda.data.user.api.Profile

interface ProfileComponent {
    val profileState: StateFlow<LoadableState<Profile>>

    fun onRefresh()
    fun onRetryClick()
    // TODO 3 dot button in topbar, где будет выпадающее меню и кнопки "эдит, делит, логаут"

}
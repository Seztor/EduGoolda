package ru.itmo.edugoolda.features.profile.presentation.viewProfile

import kotlinx.coroutines.flow.MutableStateFlow
import ru.itmo.edugoolda.core.utils.LoadableState
import ru.itmo.edugoolda.data.user.api.Profile

class FakeProfileComponent () : ProfileComponent {
    override val profileState = MutableStateFlow(LoadableState(data = Profile.MOCK))

    override fun onRefresh() {
        TODO()
    }

    override fun onRetryClick() {
        TODO("Not yet implemented")
    }
}

package ru.itmo.edugoolda.features.main.presentation.student

import kotlinx.coroutines.flow.MutableStateFlow
import ru.itmo.edugoolda.core.utils.createFakeChildStackStateFlow
import ru.itmo.edugoolda.features.profile.presentation.viewProfile.FakeProfileComponent

class FakeMainStudentComponent : MainStudentComponent {
    override val selectedTab = MutableStateFlow(MainStudentComponent.Tab.Home)
    override val stack = createFakeChildStackStateFlow(
        MainStudentComponent.Child.Profile(
            FakeProfileComponent()
        )
    )

    override fun onTabClick(tab: MainStudentComponent.Tab) = Unit
}
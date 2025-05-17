package ru.itmo.edugoolda.features.main.presentation.teacher

import kotlinx.coroutines.flow.MutableStateFlow
import ru.itmo.edugoolda.core.dialog.simple.fakeSimpleDialogControl
import ru.itmo.edugoolda.core.utils.createFakeChildStackStateFlow
import ru.itmo.edugoolda.features.profile.presentation.viewProfile.FakeProfileComponent

class FakeMainTeacherComponent : MainTeacherComponent {
    override val selectedTab = MutableStateFlow(MainTeacherComponent.Tab.Home)
    override val simpleDialogControl = fakeSimpleDialogControl(Unit)
    override val stack = createFakeChildStackStateFlow(
        MainTeacherComponent.Child.Profile(
            FakeProfileComponent()
        )
    )

    override fun onTabClick(tab: MainTeacherComponent.Tab) = Unit
    override fun onCreateLessonClick() = Unit

    override fun onCreateGroupClick() = Unit
}
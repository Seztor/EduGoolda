package ru.itmo.edugoolda.features.profile.presentation.editProfile

import com.arkivanov.decompose.ComponentContext
import ru.itmo.edugoolda.core.utils.componentScope
import ru.mobileup.kmm_form_validation.control.InputControl

class RealEditProfileComponent(
    componentContext: ComponentContext
) : EditProfileComponent, ComponentContext by componentContext {
    override val nameInputControl = InputControl(componentScope)
    override val bioInputControl = InputControl(componentScope)
    override val emailInputControl = InputControl(componentScope)

    override fun onFinishClick() {
        TODO("Not yet implemented")
    }

    override fun onCancelClick() {
        TODO("Not yet implemented")
    }

    override fun onEditPasswordClick() {
        TODO("Not yet implemented")
    }
}
package ru.itmo.edugoolda.features.profile.presentation.editPassword

import ru.mobileup.kmm_form_validation.control.InputControl

interface EditPasswordComponent {
    val passwordInputControl: InputControl
    val newPasswordInputControl: InputControl
    val repeatPasswordInputControl: InputControl

    fun onCancel()
    fun onFinish()
}
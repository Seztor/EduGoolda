package ru.itmo.edugoolda.features.profile.presentation.editProfile

import ru.mobileup.kmm_form_validation.control.InputControl

interface EditProfileComponent {
    val nameInputControl: InputControl
    val bioInputControl: InputControl
    val emailInputControl: InputControl

    fun onFinishClick()
    fun onCancelClick()
    fun onEditPasswordClick()
}
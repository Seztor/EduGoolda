package ru.itmo.edugoolda.features.profile.presentation

import com.arkivanov.decompose.ComponentContext
import dev.icerock.moko.resources.desc.desc
import dev.icerock.moko.resources.desc.strResDesc
import ru.itmo.edugoolda.core.dialog.standard.DialogButton
import ru.itmo.edugoolda.core.dialog.standard.StandardDialogData
import ru.itmo.edugoolda.core.dialog.standard.standardDialogControl
import ru.itmo.edugoolda.features.profile.R

class ProfileDetailsComponentImpl(
    componentContext: ComponentContext
) : ProfileDetailsComponent, ComponentContext by componentContext {
    override val dialog = standardDialogControl("dialog")
    fun onClick() {
        dialog.show(
            StandardDialogData(
                title = R.string.profile_dialog_delete_profile_header.strResDesc(),
                message = R.string.profile_dialog_delete_profile_text.strResDesc(),
                confirmButton = DialogButton(
                    text = "Yes".desc(),
                    action = { TODO() }
                ),
                dismissButton = DialogButton(
                    text = "No".desc(),
                    action = {}
                )
            )
        )
    }
}
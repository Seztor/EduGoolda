package ru.itmo.edugoolda.features.profile.presentation.editPassword

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.itmo.edugoolda.core.widget.text_field.AppTextField
import ru.itmo.edugoolda.features.profile.R
import ru.itmo.edugoolda.features.profile.presentation.editProfile.EditProfileComponent

@Composable
fun EditPasswordUI(
    component: EditProfileComponent,
    modifier: Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.height(16.dp)) // TODO there must be a header bar with Cancel and Apply buttons

        Text(text = stringResource(id = R.string.edit_password_top_bar_text))

        AppTextField(
            label = stringResource(id = R.string.edit_password_old_password_label),
            placeholder = stringResource(id = R.string.edit_password_old_password_label),
            inputControl = component.nameInputControl,
            // visualTransformation = ,
            modifier = modifier,
        )

        Spacer(modifier = Modifier.height(12.dp))

        AppTextField(
            label = stringResource(id = R.string.edit_password_new_password_label),
            placeholder = stringResource(id = R.string.edit_password_new_password_label),
            inputControl = component.nameInputControl,
            // visualTransformation = ,
            modifier = modifier,
        )

        Spacer(modifier = Modifier.height(12.dp))

        AppTextField(
            label = stringResource(id = R.string.edit_password_repeat_password_label),
            placeholder = stringResource(id = R.string.edit_password_repeat_password_label),
            inputControl = component.nameInputControl,
            // visualTransformation = ,
            modifier = modifier,
        )

    }
}
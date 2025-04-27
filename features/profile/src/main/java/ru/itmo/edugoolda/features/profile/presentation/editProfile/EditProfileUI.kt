package ru.itmo.edugoolda.features.profile.presentation.editProfile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.itmo.edugoolda.core.widget.text_field.AppTextField
import ru.itmo.edugoolda.features.profile.R

@Composable
fun EditProfileUI(
    component: EditProfileComponent,
    modifier: Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.height(16.dp)) // TODO it must be a header with "3 dot button"

        Text(
            text = stringResource(id = R.string.edit_profile_top_bar_text)
        )

        AppTextField(
            label = stringResource(id = R.string.edit_profile_name_hint),
            placeholder = stringResource(id = R.string.edit_profile_name_hint),
            inputControl = component.nameInputControl,
            modifier = modifier,
        )

        Spacer(modifier = Modifier.height(12.dp))

        AppTextField(
            label = stringResource(id = R.string.edit_profile_bio_hint),
            placeholder = stringResource(id = R.string.edit_profile_bio_hint),
            inputControl = component.bioInputControl,
            modifier = modifier,
        )

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider(modifier = modifier, thickness = 2.dp, color = Color.LightGray)
        Spacer(modifier = Modifier.height(12.dp))

        AppTextField(
            label = stringResource(R.string.edit_profile_email_hint),
            placeholder = stringResource(id = R.string.edit_profile_email_hint),
            inputControl = component.emailInputControl,
            modifier = modifier,
        )
    }
}
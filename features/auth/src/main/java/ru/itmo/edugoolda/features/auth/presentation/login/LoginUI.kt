package ru.itmo.edugoolda.features.auth.presentation.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.itmo.edugoolda.core.theme.custom.CustomTheme
import ru.itmo.edugoolda.core.widget.button.AppButton
import ru.itmo.edugoolda.core.widget.button.ButtonType
import ru.itmo.edugoolda.core.widget.text_field.AppTextField
import ru.itmo.edugoolda.features.auth.R

@Composable
fun LoginUi(
    component: LoginComponent,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {

        Spacer(modifier = Modifier.height(164.dp))

        Icon(
            imageVector = Icons.Default.Warning,
            modifier = Modifier.size(100.dp),
            contentDescription = "LoginIcon",
        )

        Spacer(modifier = Modifier.height(35.dp))

        Text(
            text = stringResource(id = R.string.login_title),
            fontWeight = CustomTheme.typography.title.bold.fontWeight,
            fontSize = CustomTheme.typography.title.bold.fontSize
        )

        Spacer(modifier = Modifier.height(60.dp))

        AppTextField(
            inputControl = component.emailInputControl,
            placeholder = stringResource(id = R.string.login_email_hint),
            modifier = Modifier.padding(horizontal = 22.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        AppTextField(
            inputControl = component.passwordInputControl,
            placeholder = stringResource(id = R.string.login_password_hint),
            modifier = Modifier.padding(horizontal = 22.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        AppButton(
            text = stringResource(id = R.string.login_button_enter),
            buttonType = ButtonType.Primary,
            onClick = { component.onLoginClick() },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 22.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        AppButton(
            text = stringResource(id = R.string.login_button_register),
            buttonType = ButtonType.Secondary,
            onClick = { component.onRegisterRequestClick() },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 22.dp).padding(bottom = 60.dp)
        )
    }
}

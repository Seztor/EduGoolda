package ru.itmo.edugoolda.features.auth.presentation.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.itmo.edugoolda.core.theme.AppTheme
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
    val childStack by component.childStack.collectAsState()
    val email by remember { mutableStateOf("") }
    val password by remember { mutableStateOf("") }
    AppTheme {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Spacer(modifier = Modifier.height(50.dp))

            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "LoginIcon",
            )
            Text(
                text = stringResource(id = R.string.login_title),
                fontStyle = CustomTheme.typography.title.regular.fontStyle,
                fontSize = CustomTheme.typography.title.regular.fontSize
            )

            Spacer(modifier = Modifier.height(30.dp))

            AppTextField(
                placeholder = stringResource(id = R.string.login_email_hint),
                inputControl = TODO(),
            )

            AppTextField(
                placeholder = stringResource(id = R.string.login_email_hint),
                inputControl = TODO()
            )

            Spacer(modifier = Modifier.height(60.dp))

            AppButton(
                buttonType = ButtonType.Primary,
                onClick = {},
            ) {
                Text(
                    text = stringResource(id = R.string.login_button_enter),
                    fontStyle = CustomTheme.typography.body.regular.fontStyle,
                    fontSize = CustomTheme.typography.body.regular.fontSize
                )
            }

            AppButton(
                buttonType = ButtonType.Secondary,
                onClick = {},
            ) {
                Text(
                    text = stringResource(id = R.string.login_button_register),
                    fontStyle = CustomTheme.typography.body.regular.fontStyle,
                    fontSize = CustomTheme.typography.body.regular.fontSize
                )
            }
        }
    }
}
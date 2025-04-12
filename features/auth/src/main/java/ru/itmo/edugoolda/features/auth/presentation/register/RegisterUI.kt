package ru.itmo.edugoolda.features.auth.presentation.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.itmo.edugoolda.core.theme.AppTheme
import ru.itmo.edugoolda.core.theme.custom.CustomTheme
import ru.itmo.edugoolda.core.widget.button.AppButton
import ru.itmo.edugoolda.core.widget.button.ButtonType
import ru.itmo.edugoolda.core.widget.text_field.AppTextField
import ru.itmo.edugoolda.data.user.api.UseRole
import ru.itmo.edugoolda.features.auth.R

@Composable
fun LoginUi(
    component: RegisterComponent,
    modifier: Modifier = Modifier,
) {
    val selectedRole by component.selectedRole.collectAsState()

    AppTheme {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = stringResource(id = R.string.register_title),
                fontStyle = CustomTheme.typography.title.regular.fontStyle,
                fontSize = CustomTheme.typography.title.regular.fontSize
            )

            Spacer(modifier = Modifier.height(30.dp))

            AppTextField(
                headerText = stringResource(id = R.string.register_email_header_hint),
                inputControl = component.emailInputControl,

            )

            AppTextField(
                placeholder = stringResource(id = R.string.register_password_hint),
                inputControl = component.passwordInputControl,
            )

            AppTextField(
                placeholder = stringResource(id = R.string.register_username_header_hint),
                inputControl = component.userNameInputControl,
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = stringResource(id = R.string.register_title_role),
                fontStyle = CustomTheme.typography.title.regular.fontStyle,
                fontSize = CustomTheme.typography.title.regular.fontSize
            )

            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                UseRole.entries.forEach {
                    RoleItem(
                        isSelected = it == selectedRole,
                        selectedRole = it,
                        onClick = { component.onUserRoleSelect(it) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(50.dp))

            AppButton(
                buttonType = ButtonType.Primary,
                onClick = { component.onRegisterClick() },
            ) {
                Text(
                    text = stringResource(id = R.string.register_button_register),
                    fontStyle = CustomTheme.typography.body.regular.fontStyle,
                    fontSize = CustomTheme.typography.body.regular.fontSize
                )
            }
        }
    }
}

@Composable
fun RoleItem(
    isSelected: Boolean,
    selectedRole: UseRole,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val text = stringResource(
        when (selectedRole) {
            UseRole.Teacher -> R.string.register_enum_string_teacher
            UseRole.Student -> R.string.register_enum_string_student
        }
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        RadioButton(
            selected = isSelected,
            onClick = onClick,
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(text = text)
    }
}
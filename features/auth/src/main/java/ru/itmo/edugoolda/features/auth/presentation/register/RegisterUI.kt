package ru.itmo.edugoolda.features.auth.presentation.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
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
import ru.itmo.edugoolda.data.user.api.UseRole
import ru.itmo.edugoolda.features.auth.R

@Composable
fun RegisterUi(
    component: RegisterComponent,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        IconButton(
            onClick = { component.onBackButtonClick() },
            modifier = Modifier.align(Alignment.Start)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "ArrowBack"
            )
        }

        Spacer(modifier = Modifier.height(37.dp))

        Text(
            text = stringResource(id = R.string.register_title),
            fontWeight = CustomTheme.typography.title.bold.fontWeight,
            fontSize = CustomTheme.typography.title.bold.fontSize
        )

        Spacer(modifier = Modifier.height(75.dp))

        AppTextField(
            placeholder = stringResource(id = R.string.register_email_header_hint),
            inputControl = component.emailInputControl,
            modifier = Modifier.padding(horizontal = 21.dp)
            )

        Spacer(modifier = Modifier.height(12.dp))

        AppTextField(
            placeholder = stringResource(id = R.string.register_password_hint),
            inputControl = component.passwordInputControl,
            modifier = Modifier.padding(horizontal = 21.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        AppTextField(
            placeholder = stringResource(id = R.string.register_username_header_hint),
            inputControl = component.userNameInputControl,
            modifier = Modifier.padding(horizontal = 21.dp)
        )

        Spacer(modifier = Modifier.height(19.dp))

        Text(
            text = stringResource(id = R.string.register_title_role),
            fontWeight = CustomTheme.typography.title.bold.fontWeight,
            fontSize = CustomTheme.typography.title.bold.fontSize,
            modifier = Modifier.align(Alignment.Start).padding(start = 21.dp)
        )

        Column(
            modifier = Modifier.padding(start = 8.dp),
        ) {
            UseRole.entries.forEach {
                RoleItem(
                    isSelected = it == component.selectedRole.value,
                    selectedRole = it,
                    onClick = { component.onUserRoleSelect(it) }
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        AppButton(
            text = stringResource(id = R.string.register_button_register),
            buttonType = ButtonType.Primary,
            onClick = { component.onRegisterClick() },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 22.dp).padding(bottom = 38.dp)
        )
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
        modifier = modifier.fillMaxWidth(),
    ) {
        RadioButton(
            selected = isSelected,
            onClick = onClick,
            modifier = Modifier.padding(end = 5.dp)
        )
        Text(text = text)
    }
}
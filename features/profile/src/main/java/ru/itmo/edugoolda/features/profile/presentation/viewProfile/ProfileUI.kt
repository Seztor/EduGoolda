package ru.itmo.edugoolda.features.profile.presentation.viewProfile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.itmo.edugoolda.core.theme.AppTheme
import ru.itmo.edugoolda.core.widget.PullRefreshLceWidget
import ru.itmo.edugoolda.data.user.api.Profile
import ru.itmo.edugoolda.features.profile.R

@Composable
fun ProfileUI(
    component: FakeProfileComponent,
    modifier: Modifier
) {
    val profileState by component.profileState.collectAsState()
    var expanded by remember { mutableStateOf(false) }

    PullRefreshLceWidget(
        state = profileState,
        onRefresh = component::onRefresh,
        onRetryClick = {
            component.onRetryClick()
        },
    ) { data: Profile, refreshing: Boolean ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom,
            ) {
                Text(text = stringResource(id = R.string.profile_top_bar_text), fontSize = 30.sp)

                IconButton(onClick = { expanded = !expanded }) {
                    Icon(Icons.Default.MoreVert, contentDescription = "More options")
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    DropdownMenuItem(
                        text = { Text(stringResource(id = R.string.profile_dropdown_edit)) },
                        leadingIcon = { Icon(Icons.Outlined.Edit, contentDescription = null) },
                        onClick = { /* TODO go to EditProfile page */ }
                    )
                    DropdownMenuItem(
                        text = { Text(stringResource(id = R.string.profile_dropdown_delete)) },
                        leadingIcon = { Icon(Icons.Outlined.Clear, contentDescription = null) },
                        onClick = { /* TODO show dialog window */ }
                    )
                    DropdownMenuItem(
                        text = { Text(stringResource(id = R.string.profile_dropdown_logout)) },
                        leadingIcon = {
                            Icon(
                                Icons.AutoMirrored.Outlined.ExitToApp,
                                contentDescription = null
                            )
                        },
                        onClick = {
                            /* TODO show dialog window */

                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(modifier = modifier) {
                Text(
                    text = data.name,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Medium,
                )
                Text(
                    text = data.role.toString(),
                    fontSize = 30.sp
                )
            }

            Column(modifier = modifier) {
                Text(
                    text = stringResource(id = R.string.profile_bio_header),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Medium,

                    )
                Text(
                    text = data.bio,
                    fontSize = 30.sp
                )
            }

            Column(modifier = modifier) {
                Text(
                    text = stringResource(id = R.string.profile_email_header),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Medium,
                )
                Text(
                    text = data.email,
                    fontSize = 30.sp
                )
            }

        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ProfileUIPreview() {
    AppTheme { // вне AppTheme не будет работать
        ProfileUI(
            component = FakeProfileComponent(), modifier = Modifier
        )
    }
}
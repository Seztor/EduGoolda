package ru.itmo.edugoolda.features.group.presentation.teacherGroupDetails

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.aartikov.replica.paged.PagedLoadingStatus
import ru.itmo.edugoolda.core.dialog.standard.StandardDialog
import ru.itmo.edugoolda.core.theme.AppTheme
import ru.itmo.edugoolda.core.theme.custom.CustomTheme
import ru.itmo.edugoolda.core.utils.TriggerLoadNext
import ru.itmo.edugoolda.core.widget.PullRefreshLceWidget
import ru.itmo.edugoolda.core.widget.button.AppButton
import ru.itmo.edugoolda.core.widget.button.ButtonType
import ru.itmo.edugoolda.data.group.group_info.api.GroupFullInfo
import ru.itmo.edugoolda.data.group.group_students_list.api.GroupStudentsList
import ru.itmo.edugoolda.data.group.group_students_list.api.KickType
import ru.itmo.edugoolda.features.group.R

@Composable
fun TeacherGroupDetailsUi(
    component: TeacherGroupDetailsComponent,
    modifier: Modifier = Modifier,
) {
    val groupOfStudentsState by component.groupOfStudentsState.collectAsState()
    val groupInvitationDataState by component.groupInvitationDataState.collectAsState()
    val groupInfoState by component.groupInfoState.collectAsState()

    val context = LocalContext.current

    StandardDialog(component.dialogDeleteGroup)
    StandardDialog(component.dialogKickMember)

    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .height(70.dp)
                .background(CustomTheme.colors.content.contentActive)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { component.onReturnBackRequestClick() },
                Modifier.padding(top = 15.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_left_white),
                    contentDescription = "Arrow Left",
                    tint = Color.Unspecified
                )
            }

            Text(
                text = stringResource(R.string.group_info_title),
                modifier = Modifier.padding(start = 30.dp, top = 15.dp),
                fontWeight = CustomTheme.typography.title.bold.fontWeight,
                fontSize = CustomTheme.typography.body.regular.fontSize,
                color = CustomTheme.colors.text.invert
            )
        }
        PullRefreshLceWidget(
            state = groupInfoState,
            onRefresh = component::onRefresh,
            onRetryClick = {
                component.onRetryClick()
            },
        ) { data: GroupFullInfo, _: Boolean ->
            Row(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = data.name,
                    fontWeight = CustomTheme.typography.title.boldSmallerSize.fontWeight,
                    fontSize = CustomTheme.typography.title.boldSmallerSize.fontSize,
                    color = CustomTheme.colors.text.primary,
                    modifier = Modifier.padding(top = 5.dp).width(270.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    softWrap = false
                )

                Spacer(modifier = Modifier.weight(1f))

                IconButton(
                    onClick = { component.onDialogDeleteGroup() },
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.rubbish_bin_icon),
                        contentDescription = "Rubbish bin",
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp)
        ) {
            AppButton(
                onClick = { component.onGroupCodeGenerateRequestClick() },
                text = stringResource(R.string.group_invitation_code),
                buttonType = ButtonType.Primary,
                modifier = Modifier
                    .padding(end = 20.dp)
                    .width(140.dp)
                    .height(75.dp)
            )

            AppButton(
                onClick = {
                    if (groupInvitationDataState != null) {
                        val clipboard =
                            context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                        clipboard.setPrimaryClip(
                            ClipData.newPlainText(
                                "Group Code", groupInvitationDataState!!.code.value
                            )
                        )
                    }
                },
                text = when (groupInvitationDataState) {
                    null -> ""
                    else -> groupInvitationDataState!!.code.value
                },
                buttonType = ButtonType.Secondary,
                modifier = Modifier
                    .width(140.dp)
                    .height(75.dp)
            )
        }

        Text(
            text = "${stringResource(R.string.group_members_title)}:",
            fontWeight = CustomTheme.typography.title.boldSmallerSize.fontWeight,
            fontSize = CustomTheme.typography.title.boldSmallerSize.fontSize,
            color = CustomTheme.colors.text.primary,
            modifier = Modifier.padding(top = 20.dp, bottom = 10.dp, start = 20.dp)
        )

        PullRefreshLceWidget(
            state = groupOfStudentsState,
            onRefresh = component::onRefresh,
            onRetryClick = component::onRetryClick
        ) { data: GroupStudentsList, _: Boolean ->
            val lazyListState = rememberLazyListState()
            lazyListState.TriggerLoadNext(
                pagedState = groupOfStudentsState,
                hasNextPage = groupOfStudentsState.data?.hasNextPage == true,
                callback = component::onLoadNext
            )

            LazyColumn(
                state = lazyListState,

                ) {
                items(data.users) { item ->
                    GroupItem(
                        { component.onDialogKickMember(KickType.Kick, item.id) },
                        item.name,
                    )
                }
                if (groupOfStudentsState.loadingStatus == PagedLoadingStatus.LoadingNextPage) {
                    item {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}

@Composable
fun GroupItem(
    onGroupItemClick: () -> Unit,
    name: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .height(60.dp)
                .padding(end = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Group Icon",
                modifier = Modifier
                    .padding(start = 15.dp, end = 10.dp)
                    .size(27.dp)
            )

            Text(
                text = name,
                fontWeight = CustomTheme.typography.body.regular.fontWeight,
                fontSize = CustomTheme.typography.body.regular.fontSize,
                modifier = Modifier.align(Alignment.CenterVertically).width(260.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                softWrap = false
            )

            Spacer(modifier = Modifier.weight(1f))

            IconButton(
                onClick = { onGroupItemClick() },
                modifier = Modifier.size(30.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.rubbish_bin_icon),
                    contentDescription = "Member Delete",
                    modifier = Modifier.size(25.dp)
                )
            }
        }
        Spacer(
            modifier = Modifier
                .padding(start = 40.dp, bottom = 5.dp)
                .height(1.5.dp)
                .background(Color.Gray)
                .fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGroupItem() {
    AppTheme {
        GroupItem(
            {}, "Павел"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TeacherGroupDetailsUiPreview() {
    AppTheme {
        TeacherGroupDetailsUi(component = FakeTeacherGroupDetailsComponent())
    }
}
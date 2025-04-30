package ru.itmo.edugoolda.features.group.presentation.studentGroups

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.aartikov.replica.paged.PagedLoadingStatus
import ru.itmo.edugoolda.core.theme.AppTheme
import ru.itmo.edugoolda.core.theme.custom.CustomTheme
import ru.itmo.edugoolda.core.utils.TriggerLoadNext
import ru.itmo.edugoolda.core.widget.PullRefreshLceWidget
import ru.itmo.edugoolda.core.widget.text_field.AppTextField
import ru.itmo.edugoolda.data.group.studentGroups.api.StudentGroups
import ru.itmo.edugoolda.features.group.R

@Composable
fun StudentGroupsUI(
    component: StudentGroupComponent,
    modifier: Modifier = Modifier,
) {
    val state by component.studentGroupState.collectAsState()
    PullRefreshLceWidget(
        state = state,
        onRefresh = component::onRefresh,
        onRetryClick = component::onRetryClick
    ) { data: StudentGroups, _: Boolean ->
        val lazyListState = rememberLazyListState()
        lazyListState.TriggerLoadNext(
            pagedState = state,
            hasNextPage = state.data?.hasNextPage == true,
            callback = component::onLoadNext
        )
        Column(modifier = modifier) {
            AppTextField(
                inputControl = component.groupSearchInputControl,
                placeholder = stringResource(R.string.search_students_group),
                modifier = Modifier.padding(22.dp)
            )

            LazyColumn(
                state = lazyListState,

                ) {
                items(data.groups) { item ->
                    GroupItem(
                        { component.onGroupDetailRequestClick(item.id) },
                        item.name,
                        item.subjectName
                    )
                }
                if (state.loadingStatus == PagedLoadingStatus.LoadingNextPage) {
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
    subjectName: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.height(40.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Group Icon",
                modifier = Modifier.padding(start = 15.dp, end = 10.dp)
            )

            Text(
                text = "$name, $subjectName",
                fontWeight = CustomTheme.typography.body.regular.fontWeight,
                fontSize = CustomTheme.typography.body.regular.fontSize,
                modifier = Modifier.align(Alignment.CenterVertically)
            )

            Spacer(modifier = Modifier.weight(1f))

            IconButton(
                onClick = { onGroupItemClick() }
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_forward),
                    contentDescription = "Group Icon"
                )
            }
        }
        Spacer(
            modifier = Modifier
                .padding(start = 45.dp)
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
            {}, "Группа 1", "Math"
        )
    }
}
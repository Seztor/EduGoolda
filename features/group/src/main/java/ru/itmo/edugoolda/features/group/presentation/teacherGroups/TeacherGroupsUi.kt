package ru.itmo.edugoolda.features.group.presentation.teacherGroups

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
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.aartikov.replica.paged.PagedLoadingStatus
import ru.itmo.edugoolda.core.theme.AppTheme
import ru.itmo.edugoolda.core.theme.custom.CustomTheme
import ru.itmo.edugoolda.core.utils.TriggerLoadNext
import ru.itmo.edugoolda.core.widget.PullRefreshLceWidget
import ru.itmo.edugoolda.core.widget.text_field.AppTextField
import ru.itmo.edugoolda.data.group.group_list.api.GroupInfoList
import ru.itmo.edugoolda.features.group.R

@Composable
fun TeacherGroupsUi(
    component: TeacherGroupComponent,
    modifier: Modifier = Modifier,
) {
    val state by component.teacherGroupState.collectAsState()
    Column(modifier = modifier) {
        AppTextField(
            inputControl = component.groupSearchInputControl,
            placeholder = stringResource(R.string.search_students_group),
            modifier = Modifier.padding(horizontal = 20.dp).padding(top = 35.dp, bottom = 15.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    tint = Color.Gray,
                    contentDescription = "Group Icon",
                    modifier = Modifier.size(25.dp)
                )
            }
        )

        PullRefreshLceWidget(
            state = state,
            onRefresh = component::onRefresh,
            onRetryClick = component::onRetryClick
        ) { data: GroupInfoList, _: Boolean ->
            val lazyListState = rememberLazyListState()
            lazyListState.TriggerLoadNext(
                pagedState = state,
                hasNextPage = state.data?.hasNextPage == true,
                callback = component::onLoadNext
            )
            LazyColumn(
                state = lazyListState,

                ) {
                items(data.groups) { item ->
                    TeacherGroupItem(
                        { component.onGroupDetailsRequestClick(item.id) },
                        { component.onGroupChangeFavouriteStatusRequestClick(item.id, !item.isFavourite) },
                        item.name,
                        item.subjectName,
                        item.isFavourite
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
fun TeacherGroupItem(
    onGroupItemClick: () -> Unit,
    onChangeFavourite: () -> Unit,
    name: String,
    subjectName: String,
    isFavourite: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.height(60.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Group Icon",
                modifier = Modifier.padding(start = 15.dp, end = 10.dp).size(27.dp)
            )

            Text(
                text = name,
                fontWeight = CustomTheme.typography.body.regular.fontWeight,
                fontSize = CustomTheme.typography.body.regular.fontSize,
                modifier = Modifier.align(Alignment.CenterVertically).width(150.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                softWrap = false
            )

            Text(
                text = ", $subjectName",
                fontWeight = CustomTheme.typography.body.regular.fontWeight,
                fontSize = CustomTheme.typography.body.regular.fontSize,
                modifier = Modifier.align(Alignment.CenterVertically).width(100.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                softWrap = false
            )

            Spacer(modifier = Modifier.weight(1f))

            IconButton(
                onClick = { onChangeFavourite() },
                modifier = Modifier.size(35.dp)
            ) {
                Icon(
                    painter = when (isFavourite) {
                        true -> painterResource(R.drawable.favourite_pressed)
                        false -> painterResource(R.drawable.favourite_unpressed)
                    },
                    contentDescription = "Group Icon Favorite",
                    modifier = Modifier.size(width = 20.dp, height = 23.dp)
                )
            }

            IconButton(
                onClick = { onGroupItemClick() },
                modifier = Modifier.padding(start = 10.dp, end = 10.dp).size(35.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_forward),
                    contentDescription = "Group Icon"
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
        TeacherGroupItem(
            {}, {},"Группа 1", "Math", true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TeacherGroupsUIPreview() {
    AppTheme {
        TeacherGroupsUi(component = FakeTeacherGroupComponent())
    }
}
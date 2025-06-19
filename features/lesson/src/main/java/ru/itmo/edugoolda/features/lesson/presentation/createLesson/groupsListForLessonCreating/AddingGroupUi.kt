package ru.itmo.edugoolda.features.lesson.presentation.createLesson.groupsListForLessonCreating

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
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
import ru.itmo.edugoolda.features.lesson.R

@Composable
fun AddingGroupUi(
    component: AddingGroupComponent,
    modifier: Modifier = Modifier,
) {
    val state by component.teacherGroupState.collectAsState()
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .height(70.dp)
                .background(CustomTheme.colors.content.contentActive)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { component.onCancelGroupAddingForLessonRequestClick() },
                Modifier.padding(top = 15.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_left_white),
                    contentDescription = "Arrow Left",
                    tint = Color.Unspecified
                )
            }

            Text(
                text = stringResource(R.string.lesson_adding_group_title),
                modifier = Modifier.padding(start = 30.dp, top = 15.dp),
                fontWeight = CustomTheme.typography.title.bold.fontWeight,
                fontSize = CustomTheme.typography.body.regular.fontSize,
                color = CustomTheme.colors.text.invert
            )
        }

        AppTextField(
            inputControl = component.groupSearchInputControl,
            placeholder = stringResource(R.string.search_group),
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 30.dp, bottom = 15.dp),
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
                modifier = Modifier.fillMaxSize()
                ) {
                items(data.groups) { item ->
                    TeacherGroupItem(
                        { component.onGroupAddRequestClick(item) },
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
    onGroupAddClick: () -> Unit,
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
                modifier = Modifier
                    .padding(start = 15.dp, end = 10.dp)
                    .size(27.dp)
            )

            Text(
                text = "$name, ",
                fontWeight = CustomTheme.typography.body.regular.fontWeight,
                fontSize = CustomTheme.typography.body.regular.fontSize,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .widthIn(max = 150.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                softWrap = false,
            )

            Text(
                text = subjectName,
                fontWeight = CustomTheme.typography.body.regular.fontWeight,
                fontSize = CustomTheme.typography.body.regular.fontSize,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .widthIn(max = 100.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                softWrap = false
            )

            Spacer(modifier = Modifier.weight(1f))


            IconButton(
                onClick = { onGroupAddClick() },
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp)
                    .size(35.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Group"
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
private fun PreviewGroupItem() {
    AppTheme {
        TeacherGroupItem(
            {},"Группа 1 34234324 ", "Math 134324324234324", true
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TeacherGroupsUIPreview() {
    AppTheme {
        AddingGroupUi(component = FakeAddingGroupComponent())
    }
}
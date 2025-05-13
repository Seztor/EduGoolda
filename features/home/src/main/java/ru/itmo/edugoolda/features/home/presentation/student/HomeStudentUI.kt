package ru.itmo.edugoolda.features.home.presentation.student

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.itmo.edugoolda.core.R
import ru.itmo.edugoolda.features.home.R as homeR
import ru.itmo.edugoolda.core.theme.custom.CustomTheme
import ru.itmo.edugoolda.core.utils.TriggerLoadNext
import ru.itmo.edugoolda.core.widget.PullRefreshLceWidget
import ru.itmo.edugoolda.core.widget.button.AppButton
import ru.itmo.edugoolda.core.widget.button.ButtonType
import ru.itmo.edugoolda.core.widget.join_requests.JoinRequestStudentListItem
import ru.itmo.edugoolda.core.widget.solutions.SolutionListItem
import ru.itmo.edugoolda.data.invitations.api.JoinRequestList
import ru.itmo.edugoolda.data.solutions.api.SolutionList


@Composable
fun HomeStudentUi(
    component: HomeStudentComponent,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, top = 30.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = homeR.string.logo),
                style = CustomTheme.typography.title.regular,
                fontWeight = FontWeight.Bold,

            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 10.dp),
                painter = painterResource(R.drawable.ic_40_solutions),
                contentDescription = "View"
            )
            Text(
                text = stringResource(id = homeR.string.lessons) ,
                style = CustomTheme.typography.caption.regular,
                fontWeight = FontWeight.Bold,
            )
        }

        //Solutions List
        val solutionState by component.solutionState.collectAsState()
        PullRefreshLceWidget(
            state = solutionState,
            onRefresh = component::onRefresh,
            onRetryClick = component::onRetryClick,
            modifier = modifier
        ) { data: SolutionList, _: Boolean ->
            val lazyListState = rememberLazyListState()
            lazyListState.TriggerLoadNext(
                pagedState = solutionState,
                hasNextPage = solutionState.data?.hasNextPage == true,
                callback = component::onLoadNext
            )
            LazyColumn(
                state = lazyListState,

                ) {
                items(data.solutionList.take(3)) {
                    SolutionListItem(
                        lessonName = "TODO",
                        studentName = it.sender.name,
                        date = it.date,
                        onClick = { component.onSolutionClick(it) }
                    )
                }

            }
        }


        // All Solutions button
        AppButton(
            modifier = Modifier
                .fillMaxWidth(),

            buttonType = ButtonType.Secondary,
            onClick = { component.onAllSolutionsClick() },
            contentPadding = PaddingValues(12.dp)
        ) {
            Text(
                text = stringResource(id = homeR.string.go_to_schedule),
                style = CustomTheme.typography.button.bold,
            )
            Icon(
                modifier = Modifier.padding(start = 12.dp),
                painter = painterResource(R.drawable.ic_24_app_button_path),
                contentDescription = null
            )
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 1.dp))

        // Requests section header
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 10.dp),
                painter = painterResource(R.drawable.ic_40_group_join_request),
                contentDescription = "View"
            )
            Text(
                text = stringResource(id = homeR.string.join_requests),
                style = CustomTheme.typography.caption.regular,
                fontWeight = FontWeight.Bold
            )
        }

        // Request list
        val joinRequestsState by component.joinRequestState.collectAsState()
        PullRefreshLceWidget(
            state = joinRequestsState,
            onRefresh = component::onRefresh,
            onRetryClick = component::onRetryClick,
            modifier = modifier
        ) { data: JoinRequestList, _: Boolean ->
            val lazyListState = rememberLazyListState()
            lazyListState.TriggerLoadNext(
                pagedState = joinRequestsState,
                hasNextPage = joinRequestsState.data?.hasNextPage == true,
                callback = component::onLoadNext
            )
            LazyColumn(
                state = lazyListState,

                ) {
                items(data.joinRequestList.take(3)) {
                    JoinRequestStudentListItem(
                        groupName = it.groupName,
                        date = it.date
                    )
                }

            }
        }

        // All requests button
        AppButton(
            modifier = Modifier
                .fillMaxWidth(),

            buttonType = ButtonType.Secondary,
            onClick = { component.onAllJoinRequestsClick() },
            contentPadding = PaddingValues(12.dp)
        ) {
            Text(
                text = stringResource(id = homeR.string.all_join_requests),
                style = CustomTheme.typography.button.bold,
            )
            Icon(
                modifier = Modifier.padding(start = 12.dp),
                painter = painterResource(R.drawable.ic_24_app_button_path),
                contentDescription = null
            )
        }
    }
}


package ru.itmo.edugoolda.features.home.presentation.student

import androidx.compose.foundation.layout.*
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
import ru.itmo.edugoolda.core.widget.PullRefreshLceWidget
import ru.itmo.edugoolda.core.widget.button.AppButton
import ru.itmo.edugoolda.core.widget.button.ButtonType
import ru.itmo.edugoolda.core.widget.join_requests.JoinRequestStudentListItem
import ru.itmo.edugoolda.core.widget.solutions.SolutionListItem
import ru.itmo.edugoolda.data.home.api.HomeStudentViewData
@Composable
fun HomeStudentUi(
    component: HomeStudentComponent,
    modifier: Modifier = Modifier
) {
    val mainState by component.mainState.collectAsState()
    PullRefreshLceWidget(
        state = mainState,
        onRefresh = component::onRefresh,
        onRetryClick = component::onRetryClick,
        modifier = modifier
    ) { data: HomeStudentViewData, _: Boolean ->

        Column(
            modifier = Modifier
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
                    text = stringResource(id = homeR.string.lessons),
                    style = CustomTheme.typography.caption.regular,
                    fontWeight = FontWeight.Bold,
                )
            }

            // Solutions List
            Column {
                data.solutions.take(3).forEach {
                    SolutionListItem(
                        lessonName = it.lessonName,
                        studentName = it.sender.name,
                        date = it.date,
                        onClick = { component.onSolutionClick(it) }
                    )
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
            Column {
                data.joinRequests.take(3).forEach {
                    JoinRequestStudentListItem(
                        groupName = it.groupName,
                        date = it.date
                    )
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
}

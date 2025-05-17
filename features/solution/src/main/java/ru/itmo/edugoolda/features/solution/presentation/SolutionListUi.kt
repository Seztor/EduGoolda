package ru.itmo.edugoolda.features.solution.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import me.aartikov.replica.paged.PagedLoadingStatus
import ru.itmo.edugoolda.core.utils.TriggerLoadNext
import ru.itmo.edugoolda.core.widget.PullRefreshLceWidget
import ru.itmo.edugoolda.core.widget.solutions.SolutionListItem
import ru.itmo.edugoolda.data.solutions.api.SolutionInfoList

@Composable
fun SolutionListUi(
    component: SolutionListComponent,
    modifier: Modifier = Modifier
) {
    val state by component.solutionState.collectAsState()
    PullRefreshLceWidget(
        state = state,
        onRefresh = component::onRefresh,
        onRetryClick = component::onRetryClick,
        modifier = modifier
    ) { data: SolutionInfoList, _: Boolean ->
        val lazyListState = rememberLazyListState()
        lazyListState.TriggerLoadNext(
            pagedState = state,
            hasNextPage = state.data?.hasNextPage == true,
            callback = component::onLoadNext
        )
        LazyColumn(
            state = lazyListState,
            ) {
            items(data.solutionInfoList) {
                SolutionListItem(
                    studentName = it.student.name,
                    sentAt = it.sentAt,
                    onClick = { component.onSolutionClick(it) }
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

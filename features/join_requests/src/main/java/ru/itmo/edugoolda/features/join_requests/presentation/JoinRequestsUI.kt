package ru.itmo.edugoolda.features.join_requests.presentation

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
import ru.itmo.edugoolda.data.join_requests.api.JoinRequestList
import ru.itmo.edugoolda.core.widget.invitations.InvitationListItem

@Composable
fun JoinRequestsUi(
    component: JoinRequestsComponent,
    modifier: Modifier = Modifier
) {
    val state by component.joinRequestState.collectAsState()
    PullRefreshLceWidget(
        state = state,
        onRefresh = component::onRefresh,
        onRetryClick = component::onRetryClick,
        modifier = modifier
    ) { data: JoinRequestList, _: Boolean ->
        val lazyListState = rememberLazyListState()
        lazyListState.TriggerLoadNext(
            pagedState = state,
            hasNextPage = state.data?.hasNextPage == true,
            callback = component::onLoadNext
        )
        LazyColumn(
            state = lazyListState,

            ) {
            items(data.joinRequestList) {
                JoinRequestTeacherListItem(
                    groupName = it.groupName,
                    studentName = it.sender.name,
                    date = it.date,
                    onAcceptClick = { component.onAcceptClick(it) },
                    onDeclineClick = { component.onDeclineClick(it) }
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

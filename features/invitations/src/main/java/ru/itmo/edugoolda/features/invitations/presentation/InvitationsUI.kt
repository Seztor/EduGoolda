package ru.itmo.edugoolda.features.invitations.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import me.aartikov.replica.paged.PagedLoadingStatus
import ru.itmo.edugoolda.core.utils.TriggerLoadNext
import ru.itmo.edugoolda.core.widget.PullRefreshLceWidget
import ru.itmo.edugoolda.data.invitations.api.InvitationList
import ru.itmo.edugoolda.core.widget.invitations.InvitationListItem

@Composable
fun InvitationsUi(component: InvitationsComponent) {
    val state by component.invitationState.collectAsState()
    PullRefreshLceWidget(
        state = state,
        onRefresh = component::onRefresh,
        onRetryClick = component::onRetryClick
    ) { data: InvitationList, _: Boolean ->
        val lazyListState = rememberLazyListState()
        lazyListState.TriggerLoadNext(
            pagedState = state,
            hasNextPage = state.data?.hasNextPage == true,
            callback = component::onLoadNext
        )
        LazyColumn(
            state = lazyListState,

            ) {
            items(data.invitationList) {
                InvitationListItem(
                    groupName = it.groupName,
                    studentName = it.sender.name,
                    date = it.date,
                    onAcceptClick = { component.onAcceptClick(it) },
                    onDeclineClick = { component.onAcceptClick(it) }
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

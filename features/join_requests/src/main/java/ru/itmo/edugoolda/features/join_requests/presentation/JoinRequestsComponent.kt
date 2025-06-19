package ru.itmo.edugoolda.features.join_requests.presentation

import kotlinx.coroutines.flow.StateFlow
import ru.itmo.edugoolda.core.utils.PagedState
import ru.itmo.edugoolda.data.join_requests.api.JoinRequest
import ru.itmo.edugoolda.data.join_requests.api.JoinRequestList

interface JoinRequestsComponent {
    val joinRequestState: StateFlow<PagedState<JoinRequestList>>

    fun onAcceptClick(joinRequest: JoinRequest)
    fun onDeclineClick(joinRequest: JoinRequest)
    fun onRefresh()
    fun onRetryClick()
    fun onLoadNext()
    fun onReturnBackClickRequest()

    interface Communication {
        fun onReturnBackFromJoinRequestsRequested()
    }
}
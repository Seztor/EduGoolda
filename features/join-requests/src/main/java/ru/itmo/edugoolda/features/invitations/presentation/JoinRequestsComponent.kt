package ru.itmo.edugoolda.features.invitations.presentation

import kotlinx.coroutines.flow.StateFlow
import ru.itmo.edugoolda.core.utils.PagedState
import ru.itmo.edugoolda.data.invitations.api.JoinRequest
import ru.itmo.edugoolda.data.invitations.api.JoinRequestList

interface JoinRequestsComponent {
    val joinRequestState: StateFlow<PagedState<JoinRequestList>>

    fun onAcceptClick(joinRequest: JoinRequest)
    fun onDeclineClick(joinRequest: JoinRequest)
    fun onRefresh()
    fun onRetryClick()
    fun onLoadNext()
}
package ru.itmo.edugoolda.features.lesson.presentation.teacherLessonList

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
import ru.itmo.edugoolda.data.lesson.lesson_info.api.LessonInfoList
import ru.itmo.edugoolda.core.widget.lesson.LessonInfoTeacherListItem

@Composable
fun LessonInfoListTeacherUi(
    component: LessonInfoListTeacherComponent,
    modifier: Modifier = Modifier
) {
    val state by component.lessonInfoState.collectAsState()
    PullRefreshLceWidget(
        state = state,
        onRefresh = component::onRefresh,
        onRetryClick = component::onRetryClick,
        modifier = modifier
    ) { data: LessonInfoList, _: Boolean ->
        val lazyListState = rememberLazyListState()
        lazyListState.TriggerLoadNext(
            pagedState = state,
            hasNextPage = state.data?.hasNextPage == true,
            callback = component::onLoadNext
        )
        LazyColumn(
            state = lazyListState,

            ) {
            items(data.lessonInfoList) {
                LessonInfoTeacherListItem(
                    name = it.name,
                    createdAt = it.createdAt,
                    onEditClick = { component.onEditClick(it) },
                    onDeleteClick = { component.onDeleteClick(it) }
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

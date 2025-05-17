package ru.itmo.edugoolda.features.lesson.presentation.teacherSolutionDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
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
import ru.itmo.edugoolda.core.theme.AppTheme
import ru.itmo.edugoolda.core.theme.custom.CustomTheme
import ru.itmo.edugoolda.core.widget.PullRefreshLceWidget
import ru.itmo.edugoolda.core.widget.button.AppButton
import ru.itmo.edugoolda.core.widget.button.ButtonType
import ru.itmo.edugoolda.core.widget.text_field.AppTextField
import ru.itmo.edugoolda.data.lesson.lesson_details.api.LessonStatus
import ru.itmo.edugoolda.data.lesson.lesson_details.api.SolutionDetails
import ru.itmo.edugoolda.features.lesson.R

@Composable
fun TeacherSolutionDetailsUi(
    component: TeacherSolutionDetailsComponent,
    modifier: Modifier = Modifier,
) {
    val solutionTeacherDetailsState by component.solutionTeacherDetailsState.collectAsState()

    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .height(70.dp)
                .background(CustomTheme.colors.content.contentActive)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { component.onReturnBackClick() },
                Modifier.padding(top = 15.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_left_white),
                    contentDescription = "Arrow Left",
                    tint = Color.Unspecified
                )
            }

            Text(
                text = stringResource(R.string.teacher_solution_details_title),
                modifier = Modifier.padding(start = 30.dp, top = 15.dp),
                fontWeight = CustomTheme.typography.title.bold.fontWeight,
                fontSize = CustomTheme.typography.body.regular.fontSize,
                color = CustomTheme.colors.text.invert
            )
        }

        PullRefreshLceWidget(
            state = solutionTeacherDetailsState,
            onRefresh = component::onRefresh,
            onRetryClick = component::onRetryClick
        ) { data: SolutionDetails, _: Boolean ->
            Column {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(top = 15.dp)
                ) {
                    Text(
                        text = data.lesson.name,
                        fontWeight = CustomTheme.typography.title.boldSmallerSize.fontWeight,
                        fontSize = CustomTheme.typography.title.boldSmallerSize.fontSize,
                        modifier = Modifier.width(220.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        softWrap = false
                    )

                    AppButton(
                        onClick = { component.onSetSolutionStatus(
                            when (data.status) {
                                LessonStatus.Pending -> LessonStatus.Reviewed
                                LessonStatus.Reviewed -> LessonStatus.Pending
                            }
                        ) },
                        buttonType = ButtonType.Primary,
                        text = when (data.status) {
                            LessonStatus.Pending -> stringResource(R.string.solution_button_change_checked_type_to_reviewed)
                            LessonStatus.Reviewed -> stringResource(R.string.solution_button_change_checked_type_to_pending)
                        }
                    )
                }

                Text(
                    text = "${stringResource(R.string.lesson_desription_title)}:",
                    fontWeight = CustomTheme.typography.body.bold.fontWeight,
                    fontSize = CustomTheme.typography.body.bold.fontSize,
                    color = CustomTheme.colors.text.primary,
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(top = 15.dp)
                )
                Text(
                    text = data.lesson.description
                        ?: stringResource(R.string.lesson_description_null),
                    fontWeight = CustomTheme.typography.body.regular.fontWeight,
                    fontSize = CustomTheme.typography.body.regular.fontSize,
                    color = CustomTheme.colors.text.primary,
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(top = 3.dp)
                )

                Row(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(top = 15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AppTextField(
                        inputControl = component.replyInputControl,
                        placeholder = "${stringResource(id = R.string.lesson_placeholder_reply)}...",
                        modifier = Modifier.weight(weight = 0.8f)
                    )

                    IconButton(
                        onClick = { component.onSendCommentClick() },
                        modifier = Modifier.size(45.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Send,
                            contentDescription = "send message",
                            modifier = Modifier.size(33.dp)
                        )
                    }
                }

                LazyColumn(
                    modifier = Modifier.padding(vertical = 20.dp)
                ) {
                    items(data.messages) { item ->
                        MessageItem(
                            item.author.name,
                            item.message,
                            when (item.author.id) {
                                data.lesson.teacher.id -> Arrangement.End
                                else -> Arrangement.Start
                            },
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MessageItem(
    authorName: String,
    text: String,
    alignType: Arrangement.Horizontal,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 6.dp, start = 10.dp, end = 10.dp),
        horizontalArrangement = alignType
    ) {
        Column(
            modifier = Modifier.border(
                width = 2.dp,
                shape = RoundedCornerShape(10.dp),
                color = CustomTheme.colors.border.primary
            )
        ) {
            Text(
                text = authorName,
                fontWeight = CustomTheme.typography.body.regular13.fontWeight,
                fontSize = CustomTheme.typography.body.regular13.fontSize,
                modifier = Modifier
                    .padding(start = 12.dp, top = 5.dp),
                overflow = TextOverflow.Ellipsis,
                softWrap = false,
                maxLines = 1
            )
            Text(
                text = text,
                fontWeight = CustomTheme.typography.body.regular15.fontWeight,
                fontSize = CustomTheme.typography.body.regular15.fontSize,
                modifier = Modifier

                    .padding(horizontal = 8.dp, vertical = 6.dp)
                    .width(200.dp),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MessageItemPreview() {
    AppTheme {
        MessageItem(authorName = "Pavel", text = "Привет, меня зовут Паша!", Arrangement.Start)
    }
}

@Preview(showBackground = true)
@Composable
fun TeacherSolutionDetailsUiPreview() {
    AppTheme {
        TeacherSolutionDetailsUi(component = FakeTeacherSolutionDetailsComponent())
    }
}
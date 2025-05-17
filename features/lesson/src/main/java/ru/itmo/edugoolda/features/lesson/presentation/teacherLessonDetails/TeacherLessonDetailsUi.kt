package ru.itmo.edugoolda.features.lesson.presentation.teacherLessonDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import ru.itmo.edugoolda.core.dialog.standard.StandardDialog
import ru.itmo.edugoolda.core.theme.AppTheme
import ru.itmo.edugoolda.core.theme.custom.CustomTheme
import ru.itmo.edugoolda.core.widget.PullRefreshLceWidget
import ru.itmo.edugoolda.data.lesson.lesson_details.api.LessonFullDetails
import ru.itmo.edugoolda.features.lesson.R

@Composable
fun TeacherLessonDetailsUi(
    component: TeacherLessonDetailsComponent,
    modifier: Modifier = Modifier,
) {
    val studentLessonDetailsState by component.lessonTeacherDetailsState.collectAsState()

    StandardDialog(component.dialogDeleteLesson)

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
                text = stringResource(R.string.student_lesson_details_title),
                modifier = Modifier.padding(start = 30.dp, top = 15.dp),
                fontWeight = CustomTheme.typography.title.bold.fontWeight,
                fontSize = CustomTheme.typography.body.regular.fontSize,
                color = CustomTheme.colors.text.invert
            )
        }

        PullRefreshLceWidget(
            state = studentLessonDetailsState,
            onRefresh = component::onRefresh,
            onRetryClick = component::onRetryClick
        ) { data: LessonFullDetails, _: Boolean ->
            Column {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(top = 20.dp)
                ) {
                    Column {
                        Text(
                            text = data.name,
                            fontWeight = CustomTheme.typography.title.boldSmallerSize.fontWeight,
                            fontSize = CustomTheme.typography.title.boldSmallerSize.fontSize,
                            modifier = Modifier.width(220.dp),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            softWrap = false
                        )
                    }

                    IconButton(
                        onClick = { component.onDialogLessonDelete() },
                        modifier = Modifier.size(40.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.rubbish_bin_icon),
                            contentDescription = "Rubbish bin",
                            modifier = Modifier.size(30.dp)
                        )
                    }
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
                    text = data.description ?: stringResource(R.string.lesson_description_null),
                    fontWeight = CustomTheme.typography.body.regular.fontWeight,
                    fontSize = CustomTheme.typography.body.regular.fontSize,
                    color = CustomTheme.colors.text.primary,
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(top = 3.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TeacherLessonDetailsUiPreview() {
    AppTheme {
        TeacherLessonDetailsUi(component = FakeTeacherLessonDetailsComponent())
    }
}
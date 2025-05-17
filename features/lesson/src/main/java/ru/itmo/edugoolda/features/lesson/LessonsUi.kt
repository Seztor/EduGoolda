package ru.itmo.edugoolda.features.lesson

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import ru.itmo.edugoolda.features.lesson.presentation.studentLessonDetails.StudentLessonDetailsUi
import ru.itmo.edugoolda.features.lesson.presentation.teacherLessonDetails.TeacherLessonDetailsUi
import ru.itmo.edugoolda.features.lesson.presentation.teacherSolutionDetails.TeacherSolutionDetailsUi

@Composable
fun LessonsUi(
    component: LessonsComponent,
    modifier: Modifier = Modifier
) {
    val stack by component.stack.collectAsState()

    Children(stack, modifier) {
        when (val instance = it.instance) {
            is LessonsComponent.Child.StudentLessonDetails -> StudentLessonDetailsUi(instance.component)
            is LessonsComponent.Child.TeacherLessonDetails -> TeacherLessonDetailsUi(instance.component)
            is LessonsComponent.Child.TeacherSolutionDetails -> TeacherSolutionDetailsUi(instance.component)
            LessonsComponent.Child.StudentLessonInfoList -> Unit
            LessonsComponent.Child.TeacherLessonInfoList -> Unit
            LessonsComponent.Child.TeacherLessonCreate -> Unit
            LessonsComponent.Child.TeacherSolutionsInfoList -> Unit
        }
    }
}
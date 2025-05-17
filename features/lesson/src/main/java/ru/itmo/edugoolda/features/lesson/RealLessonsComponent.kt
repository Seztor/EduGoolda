package ru.itmo.edugoolda.features.lesson

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.Serializable
import ru.itmo.edugoolda.core.ComponentFactory
import ru.itmo.edugoolda.core.utils.toStateFlow
import ru.itmo.edugoolda.data.lesson.lesson_details.api.LessonId
import ru.itmo.edugoolda.data.solutions.api.SolutionId
import ru.itmo.edugoolda.features.lesson.LessonsComponent.InitialConfiguration
import ru.itmo.edugoolda.features.lesson.presentation.studentLessonDetails.StudentLessonDetailsComponent
import ru.itmo.edugoolda.features.lesson.presentation.teacherLessonDetails.TeacherLessonDetailsComponent
import ru.itmo.edugoolda.features.lesson.presentation.teacherSolutionDetails.TeacherSolutionDetailsComponent

class RealLessonsComponent(
    componentContext: ComponentContext,
    initialConfiguration: InitialConfiguration,
    private val communication: LessonsComponent.Communication,
    private val componentFactory: ComponentFactory
) : ComponentContext by componentContext, LessonsComponent {

    private val navigation = StackNavigation<Config>()

    override val stack: StateFlow<ChildStack<*, LessonsComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = when (initialConfiguration) {
            is InitialConfiguration.StudentLessonDetails -> Config.StudentLessonDetails(
                initialConfiguration.lessonId
            )

            is InitialConfiguration.TeacherLessonDetails -> Config.TeacherLessonDetails(
                initialConfiguration.lessonId
            )

            is InitialConfiguration.TeacherSolutionDetails -> Config.TeacherSolutionDetails(
                initialConfiguration.solutionId
            )

            InitialConfiguration.StudentLessonInfoList -> Config.StudentLessonInfoList
            InitialConfiguration.TeacherLessonInfoList -> Config.TeacherLessonInfoList
            InitialConfiguration.TeacherSolutionsInfoList -> Config.TeacherSolutionsInfoList
            InitialConfiguration.TeacherLessonCreate -> Config.TeacherLessonCreate
        },
        serializer = Config.serializer(),
        childFactory = ::createChild
    ).toStateFlow(lifecycle)

    private fun createChild(
        config: Config,
        componentContext: ComponentContext
    ) = when (config) {
        is Config.StudentLessonDetails -> LessonsComponent.Child.StudentLessonDetails(
            componentFactory.createStudentLessonDetailsComponent(
                componentContext,
                config.lessonId,
                CommunicationResolver()
            )
        )

        is Config.TeacherLessonDetails -> LessonsComponent.Child.TeacherLessonDetails(
            componentFactory.createTeacherLessonDetailsComponent(
                componentContext,
                config.lessonId,
                CommunicationResolver()
            )
        )

        is Config.TeacherSolutionDetails -> LessonsComponent.Child.TeacherSolutionDetails(
            componentFactory.createTeacherSolutionDetailsComponent(
                componentContext,
                config.solutionId,
                CommunicationResolver()
            )
        )

        Config.StudentLessonInfoList -> LessonsComponent.Child.StudentLessonInfoList
        Config.TeacherLessonInfoList -> LessonsComponent.Child.TeacherLessonInfoList
        Config.TeacherSolutionsInfoList -> LessonsComponent.Child.TeacherSolutionsInfoList
        Config.TeacherLessonCreate -> LessonsComponent.Child.TeacherLessonCreate
    }

    private inner class CommunicationResolver :
        StudentLessonDetailsComponent.Communication,
        TeacherLessonDetailsComponent.Communication,
        TeacherSolutionDetailsComponent.Communication {

        override fun onReturnBackStudentLessonDetails() = goBack()
        override fun onReturnBackTeacherLessonDetails() = goBack()
        override fun onTeacherLessonDeleted() = goBack()
        override fun onReturnBackTeacherSolutionDetails() = goBack()
    }

    private fun goBack() = when {
        stack.value.items.size == 1 -> communication.onCancel()
        else -> navigation.pop()
    }

    @Serializable
    private sealed interface Config {

        @Serializable
        data class StudentLessonDetails(val lessonId: LessonId) : Config

        @Serializable
        data class TeacherLessonDetails(val lessonId: LessonId) : Config

        @Serializable
        data class TeacherSolutionDetails(val solutionId: SolutionId) : Config

        @Serializable
        data object TeacherLessonInfoList : Config

        @Serializable
        data object StudentLessonInfoList : Config

        @Serializable
        data object TeacherSolutionsInfoList : Config

        @Serializable
        data object TeacherLessonCreate : Config
    }
}
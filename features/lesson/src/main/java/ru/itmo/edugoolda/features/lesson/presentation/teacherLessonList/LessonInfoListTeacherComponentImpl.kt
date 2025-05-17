package ru.itmo.edugoolda.features.lesson.presentation.teacherLessonList

import com.arkivanov.decompose.ComponentContext
import ru.itmo.edugoolda.core.error_handling.ErrorHandler
import ru.itmo.edugoolda.core.error_handling.safeLaunch
import ru.itmo.edugoolda.core.utils.componentScope
import ru.itmo.edugoolda.core.utils.observe
import ru.itmo.edugoolda.data.lesson.lesson_details.api.LessonId
import ru.itmo.edugoolda.data.lesson.lesson_info.api.LessonInfoRepository

class LessonInfoListTeacherComponentImpl(
    componentContext: ComponentContext,
    private val communication: LessonInfoListTeacherComponent.Communication,
    private val errorHandler: ErrorHandler,
    private val lessonInfoRepository: LessonInfoRepository,

    ) : LessonInfoListTeacherComponent, ComponentContext by componentContext {

    private val lessonInfoReplica = lessonInfoRepository.lessonInfoListReplica
    override val lessonInfoState = lessonInfoReplica.observe(this, errorHandler)
    override fun onEditClick(lessonId: LessonId) {
        communication.onEditLessonRequested(lessonId)
    }

    override fun onDeleteClick(lessonId: LessonId) {
        componentScope.safeLaunch(errorHandler) {
            lessonInfoRepository.deleteLesson(lessonId)
        }
    }

    override fun onRefresh() {
        lessonInfoReplica.refresh()
    }

    override fun onRetryClick() {
        lessonInfoReplica.revalidate()
    }

    override fun onLoadNext() {
        lessonInfoReplica.loadNext()
    }
}
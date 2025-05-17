package ru.itmo.edugoolda.features.lesson.presentation.teacherLessonList

import com.arkivanov.decompose.ComponentContext
import ru.itmo.edugoolda.core.error_handling.ErrorHandler
import ru.itmo.edugoolda.core.error_handling.safeLaunch
import ru.itmo.edugoolda.core.utils.componentScope
import ru.itmo.edugoolda.core.utils.observe
import ru.itmo.edugoolda.data.lesson.lesson_info.api.LessonInfo
import ru.itmo.edugoolda.data.lesson.lesson_info.api.LessonInfoRepository

class LessonInfoListTeacherComponentImpl(
    componentContext: ComponentContext,
    private val communication: LessonInfoListTeacherComponent.Communication,
    private val errorHandler: ErrorHandler,
    private val lessonInfoRepository: LessonInfoRepository,

    ) : LessonInfoListTeacherComponent, ComponentContext by componentContext {

    private val lessonInfoReplica = lessonInfoRepository.lessonInfoListReplica
    override val lessonInfoState = lessonInfoReplica.observe(this, errorHandler)
    override fun onEditClick(lessonInfo: LessonInfo) {
        communication.onEditLessonRequested()
    }

    override fun onDeleteClick(lessonInfo: LessonInfo) {
        componentScope.safeLaunch(errorHandler) {
            lessonInfoRepository.deleteLesson(lessonInfo)
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
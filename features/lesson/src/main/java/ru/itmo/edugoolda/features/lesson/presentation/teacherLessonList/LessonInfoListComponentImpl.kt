package ru.itmo.edugoolda.features.lesson.presentation.teacherLessonList

import com.arkivanov.decompose.ComponentContext
import ru.itmo.edugoolda.core.error_handling.ErrorHandler
import ru.itmo.edugoolda.core.error_handling.safeLaunch
import ru.itmo.edugoolda.core.utils.componentScope
import ru.itmo.edugoolda.core.utils.observe
import ru.itmo.edugoolda.data.lesson.lesson_info.api.LessonInfo
import ru.itmo.edugoolda.data.lesson.lesson_info.api.LessonInfoRepository

class LessonInfoListComponentImpl(
    componentContext: ComponentContext,
    private val communication: LessonInfoListComponent.Communication,
    private val errorHandler: ErrorHandler,
    private val lessonInfoRepository: LessonInfoRepository
) : LessonInfoListComponent, ComponentContext by componentContext {

    private val invitationReplica = lessonInfoRepository.lessonInfoListReplica
    override val lessonInfoState = invitationReplica.observe(this, errorHandler)

    override fun onEditClick(lessonInfo: LessonInfo) {
        communication.onEditLessonRequested()
    }

    override fun onDeleteClick(lessonInfo: LessonInfo) {
        componentScope.safeLaunch(errorHandler) {
            lessonInfoRepository.deleteLesson(lessonInfo)
        }
    }

    override fun onRefresh() {
        invitationReplica.refresh()
    }

    override fun onRetryClick() {
        invitationReplica.revalidate()
    }

    override fun onLoadNext() {
        invitationReplica.loadNext()
    }
}
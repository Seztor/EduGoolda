package ru.itmo.edugoolda.data.lesson.lesson_info.api

import me.aartikov.replica.paged.PagedReplica
import ru.itmo.edugoolda.data.lesson.lesson_details.api.LessonId

interface LessonInfoRepository {
    val lessonInfoListReplica: PagedReplica<LessonInfoList>

    suspend fun deleteLesson(lessonId: LessonId)
}
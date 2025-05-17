package ru.itmo.edugoolda.data.lesson.lesson_info.api

import me.aartikov.replica.paged.PagedReplica

interface LessonInfoRepository {
    val lessonInfoListReplica: PagedReplica<LessonInfoList>

    suspend fun deleteLesson(lessonInfo: LessonInfo)
}
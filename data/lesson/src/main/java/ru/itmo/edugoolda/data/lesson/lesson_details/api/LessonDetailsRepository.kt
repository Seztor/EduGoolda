package ru.itmo.edugoolda.data.lesson.lesson_details.api

import me.aartikov.replica.keyed.KeyedReplica

interface LessonDetailsRepository {
    val lessonStudentDetailsReplica: KeyedReplica<LessonId, LessonStudentDetails>
    suspend fun sendMessage(lessonId: LessonId, message: String)
}
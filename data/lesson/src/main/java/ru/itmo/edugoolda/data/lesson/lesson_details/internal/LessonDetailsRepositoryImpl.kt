package ru.itmo.edugoolda.data.lesson.lesson_details.internal

import me.aartikov.replica.algebra.normal.map
import me.aartikov.replica.client.ReplicaClient
import me.aartikov.replica.keyed.KeyedReplicaSettings
import me.aartikov.replica.single.ReplicaSettings
import ru.itmo.edugoolda.data.lesson.lesson_details.api.LessonDetailsRepository
import ru.itmo.edugoolda.data.lesson.lesson_details.api.LessonId
import ru.itmo.edugoolda.data.lesson.lesson_details.internal.dto.SendMessageRequest
import ru.itmo.edugoolda.data.lesson.lesson_details.internal.dto.toDomain
import kotlin.time.Duration.Companion.minutes


internal class LessonDetailsRepositoryImpl(
    replicaClient: ReplicaClient,
    private val lessonDetailsApi: LessonDetailsApi
) : LessonDetailsRepository {

    private val _lessonStudentDetailsReplica = replicaClient.createKeyedReplica(
        name = "student lesson details replica",
        childName = {
            "child $it"
        },
        settings = KeyedReplicaSettings(maxCount = 10),
        childSettings = {
            ReplicaSettings(staleTime = 5.minutes)
        },
        fetcher = { key: LessonId ->
            lessonDetailsApi.getLessonStudentDetails(key.value)
        }
    )

    override val lessonStudentDetailsReplica = _lessonStudentDetailsReplica.map { _, data -> data.toDomain() }

    override suspend fun sendMessage(lessonId: LessonId, message: String) {
        lessonDetailsApi.sendMessageByStudent(lessonId.value, SendMessageRequest(message))
        _lessonStudentDetailsReplica.refresh(lessonId)
    }

}
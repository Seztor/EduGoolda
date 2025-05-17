package ru.itmo.edugoolda.data.solutions.internal.dto

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.itmo.edugoolda.data.solutions.api.SolutionId
import ru.itmo.edugoolda.data.solutions.internal.domain.SolutionListWithTotal
import ru.itmo.edugoolda.data.solutions.api.SolutionInfo
import ru.itmo.edugoolda.data.user.internal.dto.UserInfoDTO
import ru.itmo.edugoolda.data.user.internal.dto.toDomain

@Serializable
data class SolutionInfoListResponse(
    @SerialName("request") val solutionList: List<SolutionInfoDTO>,
    @SerialName("total") val total: Int,
)

fun SolutionInfoListResponse.toDomain(): SolutionListWithTotal = SolutionListWithTotal(
    solutionInfoList = solutionList.map { it.toDomain() },
    total = total
)

@Serializable
data class SolutionInfoDTO(
    @SerialName("id") val id: String,
    @SerialName("sent_at") val sentAt: Instant,
    @SerialName("student") val student: UserInfoDTO,
    @SerialName("status") val status: String
)
internal fun formatInstantToDateTimeString(instant: Instant): String {
    val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
    val date = localDateTime.date
    val dateString = "${date.dayOfMonth.toString().padStart(2,'0')}.${date.monthNumber.toString().padStart(2,'0')}.${date.year}"
    val time = localDateTime.time
    val timeString = "${time.hour.toString().padStart(2,'0')}:${time.minute.toString().padStart(2,'0')}"
    return "$timeString $dateString"
}

fun SolutionInfoDTO.toDomain(): SolutionInfo = SolutionInfo(
    id = SolutionId(id),
    sentAt = formatInstantToDateTimeString(sentAt),
    student = student.toDomain(),
    status = status
)
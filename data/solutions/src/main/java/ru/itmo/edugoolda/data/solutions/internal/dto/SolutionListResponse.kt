package ru.itmo.edugoolda.data.solutions.internal.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.itmo.edugoolda.data.solutions.api.SolutionId
import ru.itmo.edugoolda.data.solutions.internal.domain.SolutionListWithTotal
import ru.itmo.edugoolda.data.solutions.api.Solution
import ru.itmo.edugoolda.data.user.internal.dto.UserInfoDTO
import ru.itmo.edugoolda.data.user.internal.dto.toDomain

@Serializable
data class SolutionListResponse(
    @SerialName("request") val solutionList: List<SolutionDTO>,
    @SerialName("total") val total: Int,
)

fun SolutionListResponse.toDomain(): SolutionListWithTotal = SolutionListWithTotal(
    solutionList = solutionList.map { it.toDomain() },
    total = total
)

@Serializable
data class SolutionDTO(
    @SerialName("id") val id: String,
    @SerialName("sender") val sender: UserInfoDTO,
    @SerialName("date") val date: String
)

fun SolutionDTO.toDomain(): Solution = Solution(
    id = SolutionId(id),
    sender = sender.toDomain(),
    date = date
)
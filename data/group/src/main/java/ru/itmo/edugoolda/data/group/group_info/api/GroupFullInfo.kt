package ru.itmo.edugoolda.data.group.group_info.api

import ru.itmo.edugoolda.data.group.group_list.api.GroupId
import ru.itmo.edugoolda.data.user.api.UserId
import ru.itmo.edugoolda.data.user.api.UserInfo
import ru.itmo.edugoolda.data.user.api.UserRole

data class GroupFullInfo(
    val id: GroupId,
    val name: String,
    val description: String?,
    val subject: GroupSubject,
    val owner: UserInfo,
    val studentsCount: Int,
    val requestsCount: Int,
    val bannedCount: Int,
    val newSolutionsCount: Int,
    val tasksCount: Int,
    val isActive: Boolean,
    val createdAt: String
) {
    companion object {
        val MOCK = GroupFullInfo(
            id = GroupId("1"),
            name = "Group 1",
            description = "Top Math Group",
            subject = GroupSubject(
                id = SubjectId("12345"),
                name = "Math"
            ),
            owner = UserInfo(
                id = UserId("1234"),
                name = "Pavel",
                email = "pavel@gmail.com",
                role = UserRole.Teacher,
                isDeleted = false
            ),
            studentsCount = 5,
            requestsCount = 6,
            bannedCount = 0,
            newSolutionsCount = 2,
            tasksCount = 3,
            isActive = true,
            createdAt = "10/06/2002"
        )
    }
}

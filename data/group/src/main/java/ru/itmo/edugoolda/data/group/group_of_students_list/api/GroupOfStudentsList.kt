package ru.itmo.edugoolda.data.group.group_of_students_list.api

import ru.itmo.edugoolda.data.user.api.UserInfo
import ru.itmo.edugoolda.data.user.api.UserRole
import ru.itmo.edugoolda.data.user.api.UserId

data class GroupOfStudentsList(
    val users: List<UserInfo>,
    val hasNextPage: Boolean,
) {
    companion object {
        val MOCK = GroupOfStudentsList(
            users = listOf(
                UserInfo(
                    id = UserId("1"),
                    name = "Павел",
                    email = "USER1@mail.ru",
                    role = UserRole.Student,
                    isDeleted = false
                ),
                UserInfo(
                    id = UserId("2"),
                    name = "Серега",
                    email = "USER2@mail.ru",
                    role = UserRole.Student,
                    isDeleted = false
                ),
                UserInfo(
                    id = UserId("3"),
                    name = "Иван",
                    email = "USER3@mail.ru",
                    role = UserRole.Student,
                    isDeleted = false
                ),
                UserInfo(
                    id = UserId("4"),
                    name = "Леха",
                    email = "USER4@mail.ru",
                    role = UserRole.Student,
                    isDeleted = false
                ),
                UserInfo(
                    id = UserId("5"),
                    name = "Гоша",
                    email = "USER5@mail.ru",
                    role = UserRole.Student,
                    isDeleted = false
                )
            ),
            hasNextPage = true
        )
    }
}
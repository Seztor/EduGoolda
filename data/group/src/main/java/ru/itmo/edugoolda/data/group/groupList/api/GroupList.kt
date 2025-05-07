package ru.itmo.edugoolda.data.group.groupList.api

import ru.itmo.edugoolda.data.group.groupInfo.api.GroupInfo

data class GroupList(
    val groups: List<GroupInfo>,
    val hasNextPage: Boolean,
) {
    companion object {
        val MOCK = GroupList(
            groups = listOf(
                GroupInfo(
                    id = GroupId("1"),
                    name = "Группа 1",
                    subjectName = "Математика",
                    ownerName = "Павел",
                    isFavourite = true
                ),
                GroupInfo(
                    id = GroupId("2"),
                    name = "Группа 2",
                    subjectName = "Русский язык",
                    ownerName = "Антон",
                    isFavourite = true
                ),
                GroupInfo(
                    id = GroupId("3"),
                    name = "Группа 3",
                    subjectName = "Информатика и ИКТ",
                    ownerName = "Володя",
                    isFavourite = true
                ),
                GroupInfo(
                    id = GroupId("4"),
                    name = "Группа 4",
                    subjectName = "Литература",
                    ownerName = "Леха",
                    isFavourite = false
                ),
                GroupInfo(
                    id = GroupId("5"),
                    name = "Группа 5",
                    subjectName = "Английский язык",
                    ownerName = "Леха",
                    isFavourite = true
                ),
                GroupInfo(
                    id = GroupId("6"),
                    name = "Группа 6",
                    subjectName = "Японский язык",
                    ownerName = "Паша",
                    isFavourite = false
                ),
                GroupInfo(
                    id = GroupId("7"),
                    name = "Группа 7",
                    subjectName = "Физкультура",
                    ownerName = "Гоша",
                    isFavourite = false
                ),
                GroupInfo(
                    id = GroupId("8"),
                    name = "Группа 8",
                    subjectName = "Программирование",
                    ownerName = "Алексей",
                    isFavourite = true
                )
            ),
            hasNextPage = true
        )
    }
}

package ru.itmo.edugoolda.data.group.groupList.api

data class GroupList(
    val groups: List<GroupItemInfo>,
    val hasNextPage: Boolean,
) {
    companion object {
        val MOCK = GroupList(
            groups = listOf(
                GroupItemInfo(
                    id = GroupId("1"),
                    name = "Группа 1",
                    subjectName = "Математика",
                    ownerName = "Павел",
                    isFavourite = true
                ),
                GroupItemInfo(
                    id = GroupId("2"),
                    name = "Группа 2",
                    subjectName = "Русский язык",
                    ownerName = "Антон",
                    isFavourite = true
                ),
                GroupItemInfo(
                    id = GroupId("3"),
                    name = "Группа 3",
                    subjectName = "Информатика и ИКТ",
                    ownerName = "Володя",
                    isFavourite = true
                ),
                GroupItemInfo(
                    id = GroupId("4"),
                    name = "Группа 4",
                    subjectName = "Литература",
                    ownerName = "Леха",
                    isFavourite = false
                ),
                GroupItemInfo(
                    id = GroupId("5"),
                    name = "Группа 5",
                    subjectName = "Английский язык",
                    ownerName = "Леха",
                    isFavourite = true
                ),
                GroupItemInfo(
                    id = GroupId("6"),
                    name = "Группа 6",
                    subjectName = "Японский язык",
                    ownerName = "Паша",
                    isFavourite = false
                ),
                GroupItemInfo(
                    id = GroupId("7"),
                    name = "Группа 7",
                    subjectName = "Физкультура",
                    ownerName = "Гоша",
                    isFavourite = false
                ),
                GroupItemInfo(
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

package ru.itmo.edugoolda.data.group.studentGroups.api

data class StudentGroups(
    val groups: List<StudentGroupInfo>,
    val hasNextPage: Boolean,
) {
    companion object {
        val MOCK = StudentGroups(
            groups = listOf(
                StudentGroupInfo(
                    id = StudentGroupId("1"),
                    name = "Группа 1",
                    subjectName = "Математика",
                    ownerName = "Павел"
                ),
                StudentGroupInfo(
                    id = StudentGroupId("2"),
                    name = "Группа 2",
                    subjectName = "Русский язык",
                    ownerName = "Антон"
                ),
                StudentGroupInfo(
                    id = StudentGroupId("3"),
                    name = "Группа 3",
                    subjectName = "Информатика и ИКТ",
                    ownerName = "Володя"
                ),
                StudentGroupInfo(
                    id = StudentGroupId("4"),
                    name = "Группа 4",
                    subjectName = "Литература",
                    ownerName = "Леха"
                ),
                StudentGroupInfo(
                    id = StudentGroupId("5"),
                    name = "Группа 5",
                    subjectName = "Английский язык",
                    ownerName = "Леха"
                ),
                StudentGroupInfo(
                    id = StudentGroupId("6"),
                    name = "Группа 6",
                    subjectName = "Японский язык",
                    ownerName = "Паша"
                ),
                StudentGroupInfo(
                    id = StudentGroupId("7"),
                    name = "Группа 7",
                    subjectName = "Физкультура",
                    ownerName = "Гоша"
                ),
                StudentGroupInfo(
                    id = StudentGroupId("8"),
                    name = "Группа 8",
                    subjectName = "Программирование",
                    ownerName = "Алексей"
                )
            ),
            hasNextPage = true
        )
    }
}

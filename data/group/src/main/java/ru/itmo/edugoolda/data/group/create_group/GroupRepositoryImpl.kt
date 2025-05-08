package ru.itmo.edugoolda.data.group.create_group

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow

internal class GroupRepositoryImpl : GroupRepository {
    val subjects = MutableStateFlow(listOf("1","2","3"))
    override suspend fun createGroup(selectedSubject: String, name: String, description: String): String {
        delay(5000)
        return "hello"
    }
}

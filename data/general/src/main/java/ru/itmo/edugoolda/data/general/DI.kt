package ru.itmo.edugoolda.data.general

import org.koin.dsl.module
import ru.itmo.edugoolda.data.auth.dataAuthModule
import ru.itmo.edugoolda.data.group.dataGroupModule
import ru.itmo.edugoolda.data.user.dataUserModule
import ru.itmo.edugoolda.data.lesson.dataLessonModule

val dataModule = module {
    includes(
        dataAuthModule,
        dataUserModule,
        dataGroupModule,
        dataLessonModule
    )
}
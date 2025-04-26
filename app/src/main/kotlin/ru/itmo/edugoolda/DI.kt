package ru.itmo.edugoolda

import ru.itmo.edugoolda.core.coreModule
import ru.itmo.edugoolda.data.general.dataModule

val allModules = listOf(
    dataModule,
    coreModule(BuildConfig.BACKEND_URL),
)
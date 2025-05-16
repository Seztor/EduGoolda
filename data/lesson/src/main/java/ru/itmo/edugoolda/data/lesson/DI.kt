package ru.itmo.edugoolda.data.lesson

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.itmo.edugoolda.core.network.NetworkApiFactory
import ru.itmo.edugoolda.data.lesson.lesson_details.api.LessonDetailsRepository
import ru.itmo.edugoolda.data.lesson.lesson_details.internal.LessonDetailsApi
import ru.itmo.edugoolda.data.lesson.lesson_details.internal.LessonDetailsRepositoryImpl

val dataLessonModule = module {
    single<LessonDetailsApi> {
        get<NetworkApiFactory>().authorizedKtorfit.create()
    }

    singleOf(::LessonDetailsRepositoryImpl) bind LessonDetailsRepository::class
}
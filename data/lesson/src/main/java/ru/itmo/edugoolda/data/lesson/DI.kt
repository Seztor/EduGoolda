package ru.itmo.edugoolda.data.lesson

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.itmo.edugoolda.core.network.NetworkApiFactory
import ru.itmo.edugoolda.data.lesson.lesson_details.api.LessonDetailsRepository
import ru.itmo.edugoolda.data.lesson.lesson_details.internal.LessonDetailsApi
import ru.itmo.edugoolda.data.lesson.lesson_details.internal.LessonDetailsRepositoryImpl
import ru.itmo.edugoolda.data.lesson.lesson_info.internal.LessonInfoApi
import ru.itmo.edugoolda.data.lesson.lesson_info.internal.LessonInfoRepositoryImpl
import ru.itmo.edugoolda.data.lesson.lesson_info.api.LessonInfoRepository

val dataLessonModule = module {
    single<LessonDetailsApi> {
        get<NetworkApiFactory>().authorizedKtorfit.create()
    }

    singleOf(::LessonDetailsRepositoryImpl) bind LessonDetailsRepository::class

    single<LessonInfoApi> {
        get<NetworkApiFactory>().authorizedKtorfit.create()
    }

    singleOf(::LessonInfoRepositoryImpl) bind LessonInfoRepository::class
}

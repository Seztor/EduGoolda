package ru.itmo.edugoolda.data.solutions

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.itmo.edugoolda.core.network.NetworkApiFactory
import ru.itmo.edugoolda.data.solutions.api.SolutionRepository
import ru.itmo.edugoolda.data.solutions.internal.SolutionRepositoryImpl
import ru.itmo.edugoolda.data.solutions.internal.createSolutionApi

val solutionsModule = module {
    single {
        get<NetworkApiFactory>().authorizedKtorfit.createSolutionApi()
    }
    singleOf(::SolutionRepositoryImpl) bind SolutionRepository::class
}
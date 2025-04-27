package ru.itmo.edugoolda.data.user

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.itmo.edugoolda.core.network.NetworkApiFactory
import ru.itmo.edugoolda.data.user.api.UserInfoStore
import ru.itmo.edugoolda.data.user.api.UserRepository
import ru.itmo.edugoolda.data.user.internal.UserApi
import ru.itmo.edugoolda.data.user.internal.UserInfoStoreImpl
import ru.itmo.edugoolda.data.user.internal.UserRepositoryImpl
import ru.itmo.edugoolda.data.user.internal.createUserApi

val dataUserModule = module {
    single<UserApi> {
        get<NetworkApiFactory>().authorizedKtorfit.createUserApi()
    }
    singleOf(::UserRepositoryImpl) bind UserRepository::class
    singleOf(::UserInfoStoreImpl) bind UserInfoStore::class
}
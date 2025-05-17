package ru.itmo.edugoolda.data.join_requests

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.itmo.edugoolda.core.network.NetworkApiFactory
import ru.itmo.edugoolda.data.join_requests.api.JoinRequestRepository
import ru.itmo.edugoolda.data.join_requests.internal.JoinRequestApi
import ru.itmo.edugoolda.data.join_requests.internal.JoinRequestRepositoryImpl

val dataInvitationsModule = module {
    single<JoinRequestApi> {
        get<NetworkApiFactory>().authorizedKtorfit.create()
    }
    singleOf(::JoinRequestRepositoryImpl) bind JoinRequestRepository::class
}

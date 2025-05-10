package ru.itmo.edugoolda.data.invitations

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.itmo.edugoolda.core.network.NetworkApiFactory
import ru.itmo.edugoolda.data.invitations.api.JoinRequestRepository
import ru.itmo.edugoolda.data.invitations.internal.JoinRequestRepositoryImpl
import ru.itmo.edugoolda.data.invitations.internal.JoinRequestApi

val dataInvitationsModule = module {
    single<JoinRequestApi> {
        get<NetworkApiFactory>().authorizedKtorfit.createInvitationApi()
    }
    singleOf(::JoinRequestRepositoryImpl) bind JoinRequestRepository::class
}
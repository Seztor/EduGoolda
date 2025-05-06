package ru.itmo.edugoolda.data.invitations

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.itmo.edugoolda.core.network.NetworkApiFactory
import ru.itmo.edugoolda.data.invitations.api.InvitationRepository
import ru.itmo.edugoolda.data.invitations.internal.InvitationRepositoryImpl
import ru.itmo.edugoolda.data.invitations.internal.InvitationApi

val dataInvitationsModule = module {
    single<InvitationApi> {
        get<NetworkApiFactory>().authorizedKtorfit.createInvitationApi()
    }
    singleOf(::InvitationRepositoryImpl) bind InvitationRepository::class
}
package ru.itmo.edugoolda.data.group.groupList

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.itmo.edugoolda.core.network.NetworkApiFactory
import ru.itmo.edugoolda.data.group.groupList.api.GroupRepository
import ru.itmo.edugoolda.data.group.groupList.internal.GroupListApi
import ru.itmo.edugoolda.data.group.groupList.internal.GroupsRepositoryImpl

val dataGroupListModule = module {
    single<GroupListApi> {
        get<NetworkApiFactory>().authorizedKtorfit.create()
    }
    singleOf(::GroupsRepositoryImpl) bind GroupRepository::class
}
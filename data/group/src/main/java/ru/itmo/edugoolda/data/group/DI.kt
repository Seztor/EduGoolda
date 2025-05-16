package ru.itmo.edugoolda.data.group

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.itmo.edugoolda.core.network.NetworkApiFactory
import ru.itmo.edugoolda.data.group.create_group.api.GroupCreateRepository
import ru.itmo.edugoolda.data.group.create_group.internal.GroupCreateApi
import ru.itmo.edugoolda.data.group.create_group.internal.GroupCreateRepositoryImpl
import ru.itmo.edugoolda.data.group.group_info.api.GroupFullInfoRepository
import ru.itmo.edugoolda.data.group.group_info.internal.GroupFullInfoApi
import ru.itmo.edugoolda.data.group.group_info.internal.GroupFullInfoRepositoryImpl
import ru.itmo.edugoolda.data.group.group_invitation_data.api.GroupInvitationDataRepository
import ru.itmo.edugoolda.data.group.group_invitation_data.internal.GroupInvitationDataApi
import ru.itmo.edugoolda.data.group.group_invitation_data.internal.GroupInvitationDataRepositoryImpl
import ru.itmo.edugoolda.data.group.group_list.api.GroupListRepository
import ru.itmo.edugoolda.data.group.group_list.internal.GroupListApi
import ru.itmo.edugoolda.data.group.group_list.internal.GroupsListRepositoryImpl
import ru.itmo.edugoolda.data.group.group_students_list.api.GroupStudentsRepository
import ru.itmo.edugoolda.data.group.group_students_list.internal.GroupStudentsApi
import ru.itmo.edugoolda.data.group.group_students_list.internal.GroupStudentsRepositoryImpl

val dataGroupModule = module {
    single<GroupCreateApi> {
        get<NetworkApiFactory>().authorizedKtorfit.create()
    }
    single<GroupListApi> {
        get<NetworkApiFactory>().authorizedKtorfit.create()
    }
    single<GroupStudentsApi> {
        get<NetworkApiFactory>().authorizedKtorfit.create()
    }
    single<GroupFullInfoApi> {
        get<NetworkApiFactory>().authorizedKtorfit.create()
    }
    single<GroupInvitationDataApi> {
        get<NetworkApiFactory>().authorizedKtorfit.create()
    }

    singleOf(::GroupCreateRepositoryImpl) bind GroupCreateRepository::class
    singleOf(::GroupsListRepositoryImpl) bind GroupListRepository::class
    singleOf(::GroupStudentsRepositoryImpl) bind GroupStudentsRepository::class
    singleOf(::GroupFullInfoRepositoryImpl) bind GroupFullInfoRepository::class
    singleOf(::GroupInvitationDataRepositoryImpl) bind GroupInvitationDataRepository::class
}
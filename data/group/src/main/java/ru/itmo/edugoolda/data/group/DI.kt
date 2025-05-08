package ru.itmo.edugoolda.data.group

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.itmo.edugoolda.core.network.NetworkApiFactory
import ru.itmo.edugoolda.data.group.create_group.GroupRepository
import ru.itmo.edugoolda.data.group.create_group.GroupRepositoryImpl
import ru.itmo.edugoolda.data.group.group_info.api.GroupFullInfoRepository
import ru.itmo.edugoolda.data.group.group_info.internal.GroupFullInfoApi
import ru.itmo.edugoolda.data.group.group_info.internal.GroupFullInfoRepositoryImpl
import ru.itmo.edugoolda.data.group.group_invitation_data.api.GroupInvitationDataRepository
import ru.itmo.edugoolda.data.group.group_invitation_data.internal.GroupInvitationDataApi
import ru.itmo.edugoolda.data.group.group_invitation_data.internal.GroupInvitationDataRepositoryImpl
import ru.itmo.edugoolda.data.group.group_list.api.GroupListRepository
import ru.itmo.edugoolda.data.group.group_list.internal.GroupListApi
import ru.itmo.edugoolda.data.group.group_list.internal.GroupsListRepositoryImpl
import ru.itmo.edugoolda.data.group.group_of_students_list.api.GroupOfStudentsRepository
import ru.itmo.edugoolda.data.group.group_of_students_list.internal.GroupOfStudentsApi
import ru.itmo.edugoolda.data.group.group_of_students_list.internal.GroupOfStudentsRepositoryImpl

val dataGroupModule = module {
    single<GroupListApi> {
        get<NetworkApiFactory>().authorizedKtorfit.create()
    }
    single<GroupOfStudentsApi> {
        get<NetworkApiFactory>().authorizedKtorfit.create()
    }
    single<GroupFullInfoApi> {
        get<NetworkApiFactory>().authorizedKtorfit.create()
    }
    single<GroupInvitationDataApi> {
        get<NetworkApiFactory>().authorizedKtorfit.create()
    }

    singleOf(::GroupRepositoryImpl) bind GroupRepository::class
    singleOf(::GroupsListRepositoryImpl) bind GroupListRepository::class
    singleOf(::GroupOfStudentsRepositoryImpl) bind GroupOfStudentsRepository::class
    singleOf(::GroupFullInfoRepositoryImpl) bind GroupFullInfoRepository::class
    singleOf(::GroupInvitationDataRepositoryImpl) bind GroupInvitationDataRepository::class
}
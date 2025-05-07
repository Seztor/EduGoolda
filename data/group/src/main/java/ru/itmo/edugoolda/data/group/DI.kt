package ru.itmo.edugoolda.data.group

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.itmo.edugoolda.core.network.NetworkApiFactory
import ru.itmo.edugoolda.data.group.groupInfo.api.GroupFullInfoRepository
import ru.itmo.edugoolda.data.group.groupInfo.internal.GroupFullInfoApi
import ru.itmo.edugoolda.data.group.groupInfo.internal.GroupFullInfoRepositoryImpl
import ru.itmo.edugoolda.data.group.groupInvitationData.api.GroupInvitationDataRepository
import ru.itmo.edugoolda.data.group.groupInvitationData.internal.GroupInvitationDataApi
import ru.itmo.edugoolda.data.group.groupInvitationData.internal.GroupInvitationDataRepositoryImpl
import ru.itmo.edugoolda.data.group.groupList.api.GroupListRepository
import ru.itmo.edugoolda.data.group.groupList.internal.GroupListApi
import ru.itmo.edugoolda.data.group.groupList.internal.GroupsListRepositoryImpl
import ru.itmo.edugoolda.data.group.groupOfStudentsList.api.GroupOfStudentsRepository
import ru.itmo.edugoolda.data.group.groupOfStudentsList.internal.GroupOfStudentsApi
import ru.itmo.edugoolda.data.group.groupOfStudentsList.internal.GroupOfStudentsRepositoryImpl

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

    singleOf(::GroupsListRepositoryImpl) bind GroupListRepository::class
    singleOf(::GroupOfStudentsRepositoryImpl) bind GroupOfStudentsRepository::class
    singleOf(::GroupFullInfoRepositoryImpl) bind GroupFullInfoRepository::class
    singleOf(::GroupInvitationDataRepositoryImpl) bind GroupInvitationDataRepository::class
}
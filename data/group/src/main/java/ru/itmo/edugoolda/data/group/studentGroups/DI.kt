package ru.itmo.edugoolda.data.group.studentGroups

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.itmo.edugoolda.core.network.NetworkApiFactory
import ru.itmo.edugoolda.data.group.studentGroups.api.StudentGroupRepository
import ru.itmo.edugoolda.data.group.studentGroups.internal.StudentGroupsApi
import ru.itmo.edugoolda.data.group.studentGroups.internal.StudentGroupsRepositoryImpl

val dataStudentGroupModule = module {
    single<StudentGroupsApi> {
        get<NetworkApiFactory>().authorizedKtorfit.create()
    }
    singleOf(::StudentGroupsRepositoryImpl) bind StudentGroupRepository::class

}
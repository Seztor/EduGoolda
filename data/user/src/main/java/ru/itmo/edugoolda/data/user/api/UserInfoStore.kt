package ru.itmo.edugoolda.data.user.api

import kotlinx.coroutines.flow.Flow

interface UserInfoStore {
    suspend fun setUserId(userId: UserId)
    fun getUserId(): Flow<UserId?>

    suspend fun setUserRole(role: UserRole)
    fun getUserRole(): Flow<UserRole?>
}
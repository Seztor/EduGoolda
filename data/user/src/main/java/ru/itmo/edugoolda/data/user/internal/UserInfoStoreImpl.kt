package ru.itmo.edugoolda.data.user.internal

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.itmo.edugoolda.data.user.api.UserId
import ru.itmo.edugoolda.data.user.api.UserInfoStore
import ru.itmo.edugoolda.data.user.api.UserRole

private val Context.dataStore by preferencesDataStore(
    name = "EduGooldaDatastore"
)

internal class UserInfoStoreImpl(
    private val context: Context
) : UserInfoStore {
    companion object {
        private val USER_ID_KEY = stringPreferencesKey("USER_ID_KEY")
        private val USER_ROLE_KEY = stringPreferencesKey("USER_ROLE_KEY")
    }

    override suspend fun setUserId(userId: UserId) {
        context.dataStore.edit {
            it[USER_ID_KEY] = userId.value
        }
    }

    override fun getUserId() = context.dataStore.data.map {
        it[USER_ID_KEY]?.let(::UserId)
    }

    override suspend fun setUserRole(role: UserRole) {
        context.dataStore.edit {
            it[USER_ROLE_KEY] = role.name
        }
    }

    override fun getUserRole(): Flow<UserRole?> = context.dataStore.data.map {
        it[USER_ROLE_KEY]?.let(UserRole::valueOf)
    }
}

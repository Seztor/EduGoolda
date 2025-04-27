package ru.itmo.edugoolda.data.user.internal

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import ru.itmo.edugoolda.data.user.api.UserId
import ru.itmo.edugoolda.data.user.api.UserInfoStore

private val Context.dataStore by preferencesDataStore(
    name = "EduGooldaDatastore"
)

internal class UserInfoStoreImpl(
    private val context: Context
) : UserInfoStore {
    companion object {
        private val USER_ID_KEY = stringPreferencesKey("USER_ID_KEY")
    }

    override suspend fun setUserId(userId: UserId) {
        context.dataStore.edit {
            it[USER_ID_KEY] = userId.value
        }
    }

    override fun getUserId() = context.dataStore.data.map {
        it[USER_ID_KEY]?.let(::UserId)
    }
}

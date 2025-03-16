package com.example.instagramclone.database

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

/**
 * InstagramClonePreferences
 * InstagramClone
 *
 ** Created by Silas S. Caxias on 3/15/2025.
 *
 **/

const val STORE_NAME = "instagram_clone_store"

class StoreManager(private val context: Context) {
	companion object {
		private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(STORE_NAME)
		val USER_ID_KEY = intPreferencesKey("USER_ID_KEY")
	}
	
	suspend fun saveUserId(value: Int?) {
		value?.let {
			context.dataStore.edit {
				it[USER_ID_KEY] = value
			}
		} ?: run {
			context.dataStore.edit {
				it.remove(USER_ID_KEY)
			}
		}
	}
	
	suspend fun getUserLoggedInId(): Int? {
		return context.dataStore.data.map {
			it[USER_ID_KEY]
		}.first()
	}
}
package com.doanducdat.learndatastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserManager(context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("user_prefs")
    private val mDataStore: DataStore<Preferences> = context.dataStore
    companion object{
        val USER_NAME = stringPreferencesKey("USER_NAME")
        val PASSWORD = stringPreferencesKey("PASSWORD")
    }
    //read data
    val userNameFlow:Flow<String> = mDataStore.data.map{
        it[USER_NAME] ?: ""
    }
    val passwordFlow:Flow<String> = mDataStore.data.map{
        it[PASSWORD] ?: ""
    }
    //wrire data
    suspend fun writeData(userName:String, password:String) {
        mDataStore.edit {
            it[USER_NAME] = userName
            it[PASSWORD] = password
        }
    }
}
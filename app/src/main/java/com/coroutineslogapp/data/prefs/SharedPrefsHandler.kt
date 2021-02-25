package com.coroutineslogapp.data.prefs

import android.content.SharedPreferences
import com.coroutineslogapp.domain.model.SortType
import com.coroutineslogapp.domain.model.getDefaultListOfIndexes
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import javax.inject.Inject


private const val USERNAME_KEY = "USERNAME"
private const val TOKEN = "TOKEN"
private const val AFFILIATION_INDEXES = "AFFILIATION_INDEXES"
private const val SORT_TYPE_INDEX = "SORT_TYPE_INDEX"

class SharedPrefsHandler @Inject constructor(private val prefs: SharedPreferences) {

    fun writeCurrentUsername(username: String?) {
        val prefsEditor = prefs.edit()
        prefsEditor.putString(USERNAME_KEY, username)
        prefsEditor.apply()
    }

    fun readCurrentUsername(): String = prefs.getString(USERNAME_KEY, "") ?: ""

    fun readToken(): String = prefs.getString(TOKEN, "") ?: ""

    fun writeToken(accessToken: String) {
        val prefsEditor = prefs.edit()
        prefsEditor.putString(TOKEN, accessToken)
        prefsEditor.apply()
    }

    fun clearAll() = prefs.edit().clear().apply()

    fun readAffiliation(): List<Int> {
        val affiliationString = prefs.getString(AFFILIATION_INDEXES, "") ?: ""
        return if (affiliationString.isEmpty()) {
            getDefaultListOfIndexes()
        } else {
            val type: Type = object : TypeToken<List<Int>>() {}.type
            Gson().fromJson(affiliationString, type)
        }
    }

    fun writeAffiliation(affiliationIndexes: List<Int>) {
        val prefsEditor = prefs.edit()
        val affiliationString = Gson().toJson(affiliationIndexes)
        prefsEditor.putString(AFFILIATION_INDEXES, affiliationString)
        prefsEditor.apply()
    }

    fun readSortType() =
        prefs.getInt(SORT_TYPE_INDEX, SortType.FULL_NAME.ordinal)

    fun writeSortType(sortTypeIndex: Int) {
        val prefsEditor = prefs.edit()
        prefsEditor.putInt(SORT_TYPE_INDEX, sortTypeIndex)
        prefsEditor.apply()
    }
}

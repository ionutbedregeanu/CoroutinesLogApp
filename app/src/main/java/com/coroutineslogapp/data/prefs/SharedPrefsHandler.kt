package com.coroutineslogapp.data.prefs

import android.content.SharedPreferences
import com.coroutineslogapp.domain.model.User
import com.google.gson.Gson
import javax.inject.Inject

private const val USER_KEY = "USER"

class SharedPrefsHandler @Inject constructor(private val prefs: SharedPreferences) {

    fun writeUser(user: User) {
        val prefsEditor = prefs.edit()
        val jsonValue = Gson().toJson(user)
        prefsEditor.putString(USER_KEY, jsonValue)
        prefsEditor.apply()
    }

    fun readUser(): User? {
        val jsonValue = prefs.getString(USER_KEY, "")
        return Gson().fromJson(jsonValue, User::class.java)
    }
}

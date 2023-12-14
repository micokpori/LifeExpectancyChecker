package com.rfcreations.lifeexpectancychecker.reposiitory.preference_repository

import android.content.SharedPreferences

class UserPreferenceRepositoryImpl(
    private val sharedPreferences: SharedPreferences
) : UserPreferenceRepository {

    override fun getStringPref(key: String, defValue: String?): String? {
        return sharedPreferences.getString(key, defValue)
    }

    override fun getBooleanPref(key: String, defValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defValue)
    }

    override fun editStringPref(key: String, newValue: String) {
        sharedPreferences.edit().putString(key, newValue).apply()
    }

    override fun editBooleanPref(key: String, newValue: Boolean) {
        sharedPreferences.edit().putBoolean(key, newValue).apply()
    }
}
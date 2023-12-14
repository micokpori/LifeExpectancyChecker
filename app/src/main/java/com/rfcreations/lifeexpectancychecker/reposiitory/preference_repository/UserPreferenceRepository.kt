package com.rfcreations.lifeexpectancychecker.reposiitory.preference_repository

interface UserPreferenceRepository {

    fun getStringPref(key: String, defValue: String?): String?
    fun getBooleanPref(key: String, defValue: Boolean): Boolean

    fun editStringPref(key: String, newValue: String)
    fun editBooleanPref(key: String, newValue: Boolean)

}
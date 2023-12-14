package com.rfcreations.lifeexpectancychecker.ui.global_states

import android.content.Context
import com.rfcreations.lifeexpectancychecker.reposiitory.preference_repository.UserPreferenceRepository
import com.rfcreations.lifeexpectancychecker.R
import com.rfcreations.lifeexpectancychecker.util.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class ThemeUiState @Inject constructor(
    private val userPreferenceRepository: UserPreferenceRepository,
    appContext: Context
) {
    private val prefKeys = Constants.PrefKeys
    private val _selectedTheme =
        MutableStateFlow(
            userPreferenceRepository.getStringPref(
                prefKeys.SELECTED_THEME_KEY, appContext.getString(
                    R.string.defaultTheme
                )
            ) ?: appContext.getString(
                R.string.defaultTheme
            )
        )
    val selectedTheme = _selectedTheme.asStateFlow()

    private val _darkTheme = MutableStateFlow(false)
    val darkTheme = _darkTheme.asStateFlow()

    private val _dynamicTheme = MutableStateFlow(false)
    val dynamicTheme = _dynamicTheme.asStateFlow()

    fun updateSelectedTheme(newThemeValue: String) {
        _selectedTheme.value = newThemeValue
        userPreferenceRepository.editStringPref(prefKeys.SELECTED_THEME_KEY,newThemeValue)
    }

    fun toggleDynamicTheme() {
        _dynamicTheme.value = !_dynamicTheme.value
        userPreferenceRepository.editBooleanPref(prefKeys.DYNAMIC_THEME_KEY,_dynamicTheme.value)
    }

    fun toggleDarkThemeValue(newThemeValue: Boolean) {
        _darkTheme.value = newThemeValue
    }
}
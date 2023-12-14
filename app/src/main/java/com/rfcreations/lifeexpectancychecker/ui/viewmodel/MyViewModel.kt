package com.rfcreations.lifeexpectancychecker.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.rfcreations.lifeexpectancychecker.ui.global_states.ThemeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(val themeUiState: ThemeUiState) : ViewModel() {

    private val _selectedCountry = MutableStateFlow("")
    val selectedCountry = _selectedCountry.asStateFlow()

    private val _genderSelection = MutableStateFlow("")
    val genderSelection = _genderSelection.asStateFlow()

    private val _relationShipStatus = MutableStateFlow("")
    val relationShipStatus = _relationShipStatus.asStateFlow()

    private val _smokeSelection = MutableStateFlow("")
    val smokeSelection = _smokeSelection.asStateFlow()

    private val _socialClassSelection = MutableStateFlow("")
    val socialClassSelection = _socialClassSelection.asStateFlow()

    private val _educationSelection = MutableStateFlow("")
    val educationSelection = _educationSelection.asStateFlow()

    private val _diabeticSelection = MutableStateFlow("")
    val diabeticSelection = _diabeticSelection.asStateFlow()

    private val _jobSelection = MutableStateFlow("")
    val jobSelection = _jobSelection.asStateFlow()

    private val _bmiSelection = MutableStateFlow("")
    val bmiSelection = _bmiSelection.asStateFlow()

    private val _exerciseSelection = MutableStateFlow("")
    val exerciseSelection = _exerciseSelection.asStateFlow()

    private val _showAppThemeDialog = MutableStateFlow(false)
    val showAppThemeDialog = _showAppThemeDialog.asStateFlow()

    private val _showResultSheet = MutableStateFlow(false)
    val showResultSheet = _showResultSheet.asStateFlow()

    private val _showCountrySelectionDialog = MutableStateFlow(false)
    val showCountrySelectionDialog = _showCountrySelectionDialog.asStateFlow()

    fun uiEvent(event: UiEvent) {
        when (event) {
            is UiEvent.UpdateSelectedCountry -> {
                _selectedCountry.value = event.newCountry
            }

            is UiEvent.ToggleGenderState -> {
                _genderSelection.value = event.newGender
            }

            is UiEvent.ToggleSmokeState -> {
                _smokeSelection.value = event.newValue
            }

            is UiEvent.ToggleSocialClassState -> {
                _socialClassSelection.value = event.newClass
            }

            is UiEvent.ToggleRelationshipState -> {
                _relationShipStatus.value = event.newValue
            }

            is UiEvent.ToggleEducationState -> {
                _educationSelection.value = event.newValue
            }

            is UiEvent.ToggleBmiState -> {
                _bmiSelection.value = event.newBmiValue
            }

            is UiEvent.ToggleJobStatus -> {
                _jobSelection.value = event.newValue
            }

            is UiEvent.ToggleDiabeticState -> {
                _diabeticSelection.value = event.newValue
            }

            is UiEvent.ToggleExerciseState -> {
                _exerciseSelection.value = event.newValue
            }

            is UiEvent.ToggleShowAppThemeDialog -> {
                _showAppThemeDialog.value = !_showAppThemeDialog.value
            }

            is UiEvent.ToggleShowResultSheet -> {
                _showResultSheet.value = !_showResultSheet.value
            }

            is UiEvent.ToggleShowCountrySelectionDialog -> {
                _showCountrySelectionDialog.value = !_showCountrySelectionDialog.value
            }
        }
    }

    fun clearAllSelectedOptions() {
        _selectedCountry.value = ""
        _genderSelection.value = ""
        _smokeSelection.value = ""
        _socialClassSelection.value = ""
        _educationSelection.value = ""
        _relationShipStatus.value = ""
        _diabeticSelection.value = ""
        _jobSelection.value = ""
        _bmiSelection.value = ""
        _exerciseSelection.value = ""
    }

    //check if all radio groups or a radio group is unselected
    //if true show a toast that asks the user to select all options
    fun showReminderToast(): Boolean {
        return selectedCountry.value.isEmpty() ||
                genderSelection.value.isEmpty() ||
                smokeSelection.value.isEmpty() ||
                socialClassSelection.value.isEmpty() ||
                educationSelection.value.isEmpty() ||
                relationShipStatus.value.isEmpty() ||
                diabeticSelection.value.isEmpty() ||
                jobSelection.value.isEmpty() ||
                bmiSelection.value.isEmpty() ||
                exerciseSelection.value.isEmpty()
    }
}
package com.rfcreations.lifeexpectancychecker.ui.viewmodel

sealed class UiEvent {
    data class UpdateSelectedCountry(val newCountry:String): UiEvent()
    data class ToggleGenderState(val newGender: String) : UiEvent()
    data class ToggleSmokeState(val newValue: String) : UiEvent()
    data class ToggleSocialClassState(val newClass: String) : UiEvent()
    data class ToggleRelationshipState(val newValue: String) : UiEvent()
    data class ToggleEducationState(val newValue: String) : UiEvent()
    data class ToggleBmiState(val newBmiValue: String) : UiEvent()
    data class ToggleJobStatus(val newValue: String) : UiEvent()
    data class ToggleDiabeticState(val newValue: String) : UiEvent()
    data class ToggleExerciseState(val newValue: String) : UiEvent()
    data object ToggleShowAppThemeDialog : UiEvent()
    data object ToggleShowResultSheet: UiEvent()
    data object ToggleShowCountrySelectionDialog : UiEvent()
}
package com.rfcreations.lifeexpectancychecker.ui.screens.home_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rfcreations.lifeexpectancychecker.R
import com.rfcreations.lifeexpectancychecker.ui.screens.home_screen.components.AppBar
import com.rfcreations.lifeexpectancychecker.ui.screens.home_screen.components.AppThemeDialog
import com.rfcreations.lifeexpectancychecker.ui.screens.home_screen.components.BottomSheetLifeExpResult
import com.rfcreations.lifeexpectancychecker.ui.screens.home_screen.components.CalculateFabButton
import com.rfcreations.lifeexpectancychecker.ui.screens.home_screen.components.CountryCard
import com.rfcreations.lifeexpectancychecker.ui.screens.home_screen.components.CountrySelectionDialog
import com.rfcreations.lifeexpectancychecker.ui.screens.home_screen.components.LifeStyleRadioGroup
import com.rfcreations.lifeexpectancychecker.ui.viewmodel.MyViewModel
import com.rfcreations.lifeexpectancychecker.ui.viewmodel.UiEvent

/**
 * Top level composable
 * entry home_screen of this app
 */

@Composable
fun HomeScreen(myViewModel: MyViewModel) {
    val showAppThemeDialog = myViewModel.showAppThemeDialog.collectAsState()
    val showResultSheet = myViewModel.showResultSheet.collectAsState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(0.5f),
        topBar = {
            AppBar(
                toggleShowAppThemeDialog = { myViewModel.uiEvent(UiEvent.ToggleShowAppThemeDialog) },
                clearAllSelections = { myViewModel.clearAllSelectedOptions() })
        },
        floatingActionButton = {
            CalculateFabButton(
                checkAllOptionsAreSelected = { myViewModel.showReminderToast() },
                showResultSheet = { myViewModel.uiEvent(UiEvent.ToggleShowResultSheet) }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {

            LifeStyleSelections(
                myViewModel,
                modifier = Modifier.fillMaxWidth()
            )
        }
        if (showAppThemeDialog.value) {
            AppThemeDialog(
                themeUiState = myViewModel.themeUiState,
                toggleShowAppThemeDialog = { myViewModel.uiEvent(UiEvent.ToggleShowAppThemeDialog) }
            )
        }

        if (showResultSheet.value) {
            BottomSheetLifeExpResult(
                selectedCountry = myViewModel.selectedCountry.collectAsState().value,
                genderSelection = myViewModel.genderSelection.collectAsState().value,
                smokeSelection = myViewModel.smokeSelection.collectAsState().value,
                classSelection = myViewModel.socialClassSelection.collectAsState().value,
                educationSelection = myViewModel.educationSelection.collectAsState().value,
                marriageSelection = myViewModel.relationShipStatus.collectAsState().value,
                diabeticSelection = myViewModel.diabeticSelection.collectAsState().value,
                jobSelection = myViewModel.jobSelection.collectAsState().value,
                bmiSelection = myViewModel.bmiSelection.collectAsState().value,
                exerciseSelection = myViewModel.exerciseSelection.collectAsState().value,
                modifier = Modifier.fillMaxWidth(),
                toggleShowResultSheet = { myViewModel.uiEvent(UiEvent.ToggleShowResultSheet) }
            )
        }
    }
}


@Composable
private fun LifeStyleSelections(
    myViewModel: MyViewModel,
    modifier: Modifier = Modifier
) {
    val showCountrySelectionDialog = myViewModel.showCountrySelectionDialog.collectAsState()
    val genderOptions = stringArrayResource(id = R.array.gender_options)
    val smokeOptions = stringArrayResource(id = R.array.smoking_options)
    val socialClassOptions = stringArrayResource(id = R.array.social_class_options)
    val educationOptions = stringArrayResource(id = R.array.education_options)
    val relationshipStatusOptions = stringArrayResource(id = R.array.social_class_options)
    val diabeticOptions = stringArrayResource(id = R.array.diabetic_options)
    val jobStatusOptions = stringArrayResource(id = R.array.job_status_options)
    val bmiList = stringArrayResource(id = R.array.bmi_options)
    val exerciseOptions = stringArrayResource(id = R.array.exercise_options)
    if (showCountrySelectionDialog.value) {
        CountrySelectionDialog(
            toggleShowCountrySelectionDialog = { myViewModel.uiEvent(UiEvent.ToggleShowCountrySelectionDialog) },
            updateSelectedCountry = { newCountry ->
                myViewModel.uiEvent(
                    UiEvent.UpdateSelectedCountry(
                        newCountry
                    )
                )
            }
        )
    }
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(
            space = 8.dp,
            alignment = Alignment.CenterVertically
        )
    ) {
        item {
            CountryCard(
                modifier = modifier,
                selectedCountry = myViewModel.selectedCountry.collectAsState().value
            ) {
                myViewModel.uiEvent(UiEvent.ToggleShowCountrySelectionDialog)
            }

        LifeStyleRadioGroup(
            title = stringResource(id = R.string.are_you_male_or_female),
            imageDrawable = R.drawable.gender,
            radioButtonOptions = genderOptions,
            selectedOption = myViewModel.genderSelection.collectAsState().value,
            modifier = modifier
        ) { newGender ->
            myViewModel.uiEvent(UiEvent.ToggleGenderState(newGender))
        }

        LifeStyleRadioGroup(
            title = stringResource(id = R.string.do_you_smoke),
            imageDrawable = R.drawable.smoke,
            radioButtonOptions = smokeOptions,
            selectedOption = myViewModel.smokeSelection.collectAsState().value,
            modifier = modifier
        ) { newSmokeValue ->
            myViewModel.uiEvent(UiEvent.ToggleSmokeState(newSmokeValue))
        }

        LifeStyleRadioGroup(
            title = stringResource(id = R.string.social_class),
            imageDrawable = R.drawable.class_image,
            radioButtonOptions = socialClassOptions,
            selectedOption = myViewModel.socialClassSelection.collectAsState().value,
            modifier = modifier
        ) { newSocialClass ->
            myViewModel.uiEvent(UiEvent.ToggleSocialClassState(newSocialClass))
        }


        LifeStyleRadioGroup(
            title = stringResource(id = R.string.what_is_your_education_level),
            imageDrawable = R.drawable.school,
            radioButtonOptions = educationOptions,
            selectedOption = myViewModel.educationSelection.collectAsState().value,
            modifier = modifier
        ) { newEdu ->
            myViewModel.uiEvent(UiEvent.ToggleEducationState(newEdu))
        }

        LifeStyleRadioGroup(
            title = stringResource(id = R.string.relationship_status),
            imageDrawable = R.drawable.love,
            radioButtonOptions = relationshipStatusOptions,
            selectedOption = myViewModel.relationShipStatus.collectAsState().value,
            modifier = modifier
        ) { newRelationShipValue ->
            myViewModel.uiEvent(UiEvent.ToggleRelationshipState(newRelationShipValue))
        }

        LifeStyleRadioGroup(
            title = stringResource(id = R.string.are_you_diabetic),
            imageDrawable = R.drawable.virus,
            radioButtonOptions = diabeticOptions,
            selectedOption = myViewModel.diabeticSelection.collectAsState().value,
            modifier = modifier
        ) { newDiabeticValue ->
            myViewModel.uiEvent(UiEvent.ToggleDiabeticState(newDiabeticValue))
        }

        LifeStyleRadioGroup(
            title = stringResource(id = R.string.do_you_have_a_job),
            imageDrawable = R.drawable.job,
            radioButtonOptions = jobStatusOptions,
            selectedOption = myViewModel.jobSelection.collectAsState().value,
            modifier = modifier
        ) { newJobValue ->
            myViewModel.uiEvent(UiEvent.ToggleJobStatus(newJobValue))
        }

        LifeStyleRadioGroup(
            title = stringResource(id = R.string.whats_your_bmi),
            imageDrawable = R.drawable.bmi,
            radioButtonOptions = bmiList,
            selectedOption = myViewModel.bmiSelection.collectAsState().value,
            modifier = modifier
        ) { newBmi ->
            myViewModel.uiEvent(UiEvent.ToggleBmiState(newBmi))
        }

        LifeStyleRadioGroup(
            title = stringResource(id = R.string.what_s_your_exercise_level),
            imageDrawable = R.drawable.exercise,
            radioButtonOptions = exerciseOptions,
            selectedOption = myViewModel.exerciseSelection.collectAsState().value,
            modifier = modifier
        ) { newExerciseValue ->
            myViewModel.uiEvent(UiEvent.ToggleExerciseState(newExerciseValue))
        }
    }
}
}
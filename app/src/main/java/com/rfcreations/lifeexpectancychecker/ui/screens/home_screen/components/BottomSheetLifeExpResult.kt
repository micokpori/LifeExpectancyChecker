package com.rfcreations.lifeexpectancychecker.ui.screens.home_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.rfcreations.lifeexpectancychecker.R
import com.rfcreations.lifeexpectancychecker.util.calculateLifeExpectancy

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetLifeExpResult(
    selectedCountry: String,
    genderSelection: String,
    smokeSelection: String,
    classSelection: String,
    educationSelection: String,
    marriageSelection: String,
    diabeticSelection: String,
    jobSelection: String,
    bmiSelection: String,
    exerciseSelection: String,
    modifier: Modifier = Modifier,
    toggleShowResultSheet: () -> Unit
) {
    val context = LocalContext.current
    val windowInsets = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    val lifeExpectancy =
        calculateLifeExpectancy(
            context,
            selectedCountry,
            genderSelection,
            smokeSelection,
            classSelection,
            educationSelection,
            marriageSelection,
            diabeticSelection,
            jobSelection,
            bmiSelection,
            exerciseSelection
        )

    ModalBottomSheet(
        onDismissRequest = toggleShowResultSheet,
        dragHandle = {}
    ) {
        Column(modifier.padding(bottom = windowInsets)) {

            Row(
                modifier,
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = stringResource(id = R.string.collapse),
                    textAlign = TextAlign.End,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .clickable {
                            toggleShowResultSheet()
                        }
                        .padding(horizontal = 18.dp, vertical = 18.dp)
                )
            }
            Column(modifier = modifier.padding(horizontal = 8.dp)) {

                Text(
                    text = stringResource(R.string.results),
                    style = MaterialTheme.typography.titleLarge
                )
                Divider(color = MaterialTheme.colorScheme.outlineVariant)

                Spacer(modifier = modifier.height(12.dp))
                Text(
                    text = stringResource(R.string.your_country, selectedCountry),
                    style = MaterialTheme.typography.titleMedium,
                )

                Text(
                    text = stringResource(R.string.life_expectancy_years, lifeExpectancy.first),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = stringResource(
                        R.string.average_life_expectancy_years,
                        lifeExpectancy.second
                    ),
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier.height(8.dp))

                Text(
                    text = stringResource(R.string.how_to_improve_life_expectancy),
                    modifier,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    textDecoration = TextDecoration.Underline
                )

                Spacer(modifier.height(8.dp))

                Text(
                    text = stringResource(id = R.string.step_to_improve_life_exp),
                    style = MaterialTheme.typography.bodyLarge,
                )

            }
        }
    }
}
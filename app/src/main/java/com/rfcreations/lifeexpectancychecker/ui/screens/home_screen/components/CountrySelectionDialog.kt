package com.rfcreations.lifeexpectancychecker.ui.screens.home_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.rfcreations.lifeexpectancychecker.R
import com.rfcreations.lifeexpectancychecker.util.getAllCountries

@Composable
fun CountrySelectionDialog(
    modifier: Modifier = Modifier,
    toggleShowCountrySelectionDialog: () -> Unit,
    updateSelectedCountry: (String) -> Unit
) {
    val context = LocalContext.current
    val searchQuery = rememberSaveable { mutableStateOf("") }
    val countryList = getAllCountries(context).filter {
        it.contains(searchQuery.value, true)
    }

    AlertDialog(
        onDismissRequest = {
            toggleShowCountrySelectionDialog()
        },
        modifier = modifier
            .height(600.dp),
        confirmButton = {
            /*TextButton(onClick = countryDialogChanger) {
                Text(text = stringResource(R.string.close))
            }*/
        },
        text = {
            Column {
                SearchBar(modifier, searchQuery)
                LazyColumn {
                    items(countryList) { country ->
                        IndividualCountryColumn(
                            modifier = modifier,
                            country = country,
                            toggleShowCountrySelectionDialog,
                            updateSelectedCountry = { newCountry -> updateSelectedCountry(newCountry) }
                        )
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun SearchBar(
    modifier: Modifier, searchQuery: MutableState<String>
) {
    val localKeyBoardController = LocalSoftwareKeyboardController.current
    TextField(
        modifier = modifier.fillMaxWidth(),
        value = searchQuery.value,
        onValueChange = { searchQuery.value = it },
        label = { Text(text = stringResource(id = R.string.search_country)) },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { localKeyBoardController?.hide() }),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        },
        trailingIcon = {
            if (searchQuery.value.isNotEmpty()) {
                Icon(Icons.Default.Clear,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = modifier.clickable {
                        searchQuery.value = ""
                    }
                )
            }
        },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent
        )
    )
}

@Composable
private fun IndividualCountryColumn(
    modifier: Modifier = Modifier,
    country: String,
    toggleShowCountrySelectionDialog: () -> Unit,
    updateSelectedCountry: (String) -> Unit,
) {
    Row(
        modifier
            .clickable {
                updateSelectedCountry(country)
                toggleShowCountrySelectionDialog()
            }
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = country,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
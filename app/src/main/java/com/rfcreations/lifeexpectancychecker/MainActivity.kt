package com.rfcreations.lifeexpectancychecker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.rfcreations.lifeexpectancychecker.ui.navigation.NavigationSetup
import com.rfcreations.lifeexpectancychecker.ui.theme.LifeExpectancyCheckerTheme
import com.rfcreations.lifeexpectancychecker.ui.viewmodel.MyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val myViewModel = hiltViewModel<MyViewModel>()
            LifeExpectancyCheckerTheme(
                themeUiState = myViewModel.themeUiState
            ) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationSetup(myViewModel = myViewModel)
                }
            }
        }
    }
}


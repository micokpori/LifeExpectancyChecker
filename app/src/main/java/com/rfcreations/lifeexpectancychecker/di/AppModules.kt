package com.rfcreations.lifeexpectancychecker.di

import android.content.Context
import android.content.SharedPreferences
import com.rfcreations.lifeexpectancychecker.reposiitory.preference_repository.UserPreferenceRepository
import com.rfcreations.lifeexpectancychecker.reposiitory.preference_repository.UserPreferenceRepositoryImpl
import com.rfcreations.lifeexpectancychecker.ui.global_states.ThemeUiState
import com.rfcreations.lifeexpectancychecker.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModules {

    @Provides
    @Singleton
    fun providesSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        val prefName = Constants.PrefKeys.PREF_NAME
        return context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providesUserPreferenceRepository(sharedPreferences: SharedPreferences): UserPreferenceRepository {
        return UserPreferenceRepositoryImpl(sharedPreferences)
    }

    @Singleton
    @Provides
    fun providesThemeUiState(
        @ApplicationContext context: Context, userPreferenceRepository: UserPreferenceRepository
    ): ThemeUiState = ThemeUiState(userPreferenceRepository, context)
}
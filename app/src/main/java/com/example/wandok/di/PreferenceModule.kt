package com.example.wandok.di

import android.content.Context
import com.example.wandok.database.AppPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object PreferenceModule {
    @Provides
    @Singleton
    fun providePreference(@ApplicationContext context: Context): AppPreferences {
        return AppPreferences(context)
    }
}
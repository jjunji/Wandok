package com.example.wandok.di

import android.content.Context
import androidx.room.Room
import com.example.wandok.database.BookDao
import com.example.wandok.database.BookDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): BookDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            BookDatabase::class.java,
            "Tasks.db"
        ).build()
    }

    @Provides
    fun provideBookDao(database: BookDatabase): BookDao = database.bookDao()
}

package com.example.moodjournal.core.di

import android.content.Context
import androidx.room.Room
import com.example.moodjournal.data.local.AppDatabase
import com.example.moodjournal.data.local.dao.MoodCardDao
import com.example.moodjournal.data.local.dao.UserNameDao
import com.example.moodjournal.domain.repository.Repository
import com.example.moodjournal.data.repository.impl.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "app_db").build()

    @Provides
    fun provideUserNameDao(db: AppDatabase): UserNameDao = db.UserNameDao()

    @Provides
    fun provideMoodCardDao(db: AppDatabase): MoodCardDao = db.MoodCardDao()
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWelcomeRepository(
        impl: RepositoryImpl
    ): Repository
}
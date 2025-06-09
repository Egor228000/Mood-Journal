package com.example.moodjournal.data.local.entity.di

import android.content.Context
import androidx.room.Room
import com.example.moodjournal.data.local.entity.AppDatabase
import com.example.moodjournal.data.local.entity.repository.welcome.WelcomeRepositoryImpl
import com.example.moodjournal.data.local.entity.welcome.WelcomeDao
import com.example.moodjournal.domain.usecase.repository.welcome.WelcomeRepository
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
    fun provideWelcomeDao(db: AppDatabase): WelcomeDao = db.welcomeDao()
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWelcomeRepository(
        impl: WelcomeRepositoryImpl
    ): WelcomeRepository
}
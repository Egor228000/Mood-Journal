package com.example.moodjournal.data.local.entity

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moodjournal.data.local.entity.welcome.WelcomeDao
import com.example.moodjournal.data.local.entity.welcome.WelcomeEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton



@Database(entities = [WelcomeEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun welcomeDao(): WelcomeDao
}


package com.example.moodjournal.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moodjournal.data.local.dao.MoodCardDao
import com.example.moodjournal.data.local.dao.UserNameDao
import com.example.moodjournal.data.local.entity.MoodCardEnity
import com.example.moodjournal.data.local.entity.WelcomeEntity

@Database(entities = [WelcomeEntity::class, MoodCardEnity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun MoodCardDao(): MoodCardDao
    abstract fun UserNameDao(): UserNameDao
}
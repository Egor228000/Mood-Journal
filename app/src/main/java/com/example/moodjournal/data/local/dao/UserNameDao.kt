package com.example.moodjournal.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moodjournal.data.local.entity.WelcomeEntity

@Dao
interface UserNameDao {
    @Query("SELECT * FROM WelcomeEntity WHERE id = :id")
    suspend fun getWelcomeName(id: Int): WelcomeEntity?

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertWelcomeName(welcome: WelcomeEntity)
}
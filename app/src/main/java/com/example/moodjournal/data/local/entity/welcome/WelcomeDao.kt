package com.example.moodjournal.data.local.entity.welcome

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WelcomeDao {
    @Query("SELECT * FROM WelcomeEntity WHERE id = :id")
    suspend fun getWelcomeName(id: Int): WelcomeEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWelcomeName(welcome: WelcomeEntity)
}
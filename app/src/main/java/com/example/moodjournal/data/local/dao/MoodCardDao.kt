package com.example.moodjournal.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.moodjournal.data.local.entity.MoodCardEnity

@Dao
interface MoodCardDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertMoodCard(moodCard: MoodCardEnity)
}

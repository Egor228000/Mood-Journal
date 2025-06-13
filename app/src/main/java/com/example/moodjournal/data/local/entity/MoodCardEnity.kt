package com.example.moodjournal.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MoodCardEnity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val mood: String,
    val pathFile: String = "",
    val date: String
)


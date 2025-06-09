package com.example.moodjournal.data.local.entity.welcome

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WelcomeEntity(
    @PrimaryKey val id: Int,
    val name: String
)

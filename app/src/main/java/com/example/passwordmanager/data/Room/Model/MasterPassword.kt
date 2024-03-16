package com.example.passwordmanager.data.Room.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MasterPassword")
data class MasterPassword(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val masterPassword: String
)
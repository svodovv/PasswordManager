package com.example.passwordmanager.data.Room.Model

import android.content.ContentValues
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserAuth")
data class UserAuthenticated(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val userAuthenticated: Boolean
){
    fun toContentValues(): ContentValues {
        val values = ContentValues()
        values.put("userAuthenticated", userAuthenticated)
        return values
    }
}
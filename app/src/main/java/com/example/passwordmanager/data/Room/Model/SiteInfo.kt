package com.example.passwordmanager.data.Room.Model

import android.content.ContentValues
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SiteInfo")
data class SiteInfo(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val url: String,
    val name: String,
    val password: String
){
    fun toContentValues(): ContentValues {
        val values = ContentValues()
        values.put("url", url)
        values.put("name", name)
        values.put("password", password)
        return values
    }
}
package com.example.passwordmanager.data.Room.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.passwordmanager.data.Room.Model.MasterPassword
import kotlinx.coroutines.flow.Flow

@Dao
interface MasterPasswordDao {

    @Query("SELECT * FROM MasterPassword")
    suspend fun getMasterPassword(): MasterPassword?

}
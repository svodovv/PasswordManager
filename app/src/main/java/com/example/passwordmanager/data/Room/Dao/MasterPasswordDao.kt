package com.example.passwordmanager.data.Room.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.passwordmanager.data.Room.Model.MasterPassword
import kotlinx.coroutines.flow.Flow

@Dao
interface MasterPasswordDao {

    @Query("SELECT * FROM MasterPassword ")
    fun getMasterPassword(): LiveData<MasterPassword>

    @Insert
    suspend fun insertPassword(password: MasterPassword)

}
package com.example.passwordmanager.data.Room.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.example.passwordmanager.data.Room.Model.UserAuthenticated
import kotlinx.coroutines.flow.Flow

@Dao
interface UserAuthenticatedDao {

    @Query("SELECT * FROM UserAuth  WHERE id = 1 ")
    fun getMasterPassword(): LiveData<UserAuthenticated>

    @Update
    suspend fun updatePassBoolById(userAuthenticated: UserAuthenticated)
}
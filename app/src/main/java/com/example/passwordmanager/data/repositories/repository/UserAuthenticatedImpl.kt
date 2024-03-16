package com.example.passwordmanager.data.repositories.repository

import androidx.lifecycle.LiveData
import com.example.passwordmanager.data.Room.Model.UserAuthenticated
import com.example.passwordmanager.data.Room.SiteInfoDatabase
import javax.inject.Inject

class UserAuthenticatedImpl @Inject constructor(
    private val db: SiteInfoDatabase
) {
    fun getUserAuth(): LiveData<UserAuthenticated> {
       return db.userAuth().getMasterPassword()
    }

    suspend fun userSavePass(){
        db.userAuth().updatePassBoolById(UserAuthenticated(1, true))
    }

}
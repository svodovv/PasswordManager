package com.example.passwordmanager.data.repositories.repository

import AESCrypt
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.passwordmanager.data.Room.Model.MasterPassword
import com.example.passwordmanager.data.Room.SiteInfoDatabase
import javax.inject.Inject

class PasswordRepositoryImpl @Inject constructor(
    private val db: SiteInfoDatabase
) {


    suspend fun insertPassword(password: String) {
        val passwordEncrypt = MasterPassword(
            id = 1, masterPassword = AESCrypt.encrypt(password)
        )

        db.materPasswordDao().insertPassword(
            passwordEncrypt
        )
    }

    fun userAuthenticated(password: String): LiveData<Boolean?> {
        val resultLiveData = MutableLiveData<Boolean?>()
        val passwordEncrypt = AESCrypt.encrypt(password)

        db.materPasswordDao().getMasterPassword().observeForever { masterPassword ->
            masterPassword?.let {
                val passDb = it.masterPassword
                resultLiveData.value = passDb == passwordEncrypt

            }
        }
        return resultLiveData
    }

}
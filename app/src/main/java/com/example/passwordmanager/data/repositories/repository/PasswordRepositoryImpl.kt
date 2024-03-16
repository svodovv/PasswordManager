package com.example.passwordmanager.data.repositories.repository

import com.example.passwordmanager.data.EncryptionUtils
import com.example.passwordmanager.data.Room.SiteInfoDatabase
import com.omgupsapp.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

class PasswordRepositoryImpl @Inject constructor(
    private val db: SiteInfoDatabase
) {
    fun getMasterPassword(): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading())
            val passBool = db.materPasswordDao().getMasterPassword()?.masterPassword == null
            emit(Resource.Success(passBool))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Ошибка в получении пароля"))
        }
    }



}
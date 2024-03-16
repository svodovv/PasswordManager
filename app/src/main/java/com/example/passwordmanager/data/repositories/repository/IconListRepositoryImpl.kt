package com.example.passwordmanager.data.repositories.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.passwordmanager.data.Room.SiteInfoDatabase
import com.example.passwordmanager.data.repositories.model.IconUrlToNameModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class IconListRepositoryImpl @Inject constructor(
    private val db: SiteInfoDatabase
) {
      fun getIconUrls(): LiveData<List<IconUrlToNameModel>> {
        return db.siteInfoDao().getUrlIcons().map {
            it.map { siteInfo ->
                IconUrlToNameModel(
                    name = siteInfo.name,
                    url = siteInfo.url
                )
            }
        }
    }

    suspend fun deleteIconByName(name:String){
        db.siteInfoDao().deleteIconByName(name)
    }
}
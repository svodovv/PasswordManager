package com.example.passwordmanager.data.repositories.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.passwordmanager.data.Room.SiteInfoDatabase
import com.example.passwordmanager.data.repositories.model.SiteInfoModel
import javax.inject.Inject

class IconListRepositoryImpl @Inject constructor(
    private val db: SiteInfoDatabase
) {
      fun getIconUrls(): LiveData<List<SiteInfoModel>> {
        return db.siteInfoDao().getUrlIcons().map {
            it.map { siteInfo ->

                SiteInfoModel(
                    id = siteInfo.id,
                    name = siteInfo.name,
                    url = siteInfo.url,
                    password = siteInfo.password
                )
            }
        }
    }


}
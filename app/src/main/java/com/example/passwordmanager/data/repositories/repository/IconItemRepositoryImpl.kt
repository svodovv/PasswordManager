package com.example.passwordmanager.data.repositories.repository

import AESCrypt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.passwordmanager.data.Room.Model.SiteInfo
import com.example.passwordmanager.data.Room.SiteInfoDatabase
import com.example.passwordmanager.ui.screens.IconItemAdd.IconItemAddState
import com.example.passwordmanager.ui.screens.IconItemEdit.SiteInfoEditState
import javax.inject.Inject

class IconItemRepositoryImpl @Inject constructor(
    private val db: SiteInfoDatabase
) {
    fun getSiteInfo(name: String): LiveData<SiteInfo> {
        val mutableLiveData = MutableLiveData<SiteInfo>()
        db.siteInfoDao().getSiteDetails(name).observeForever {
            if (it != null) {
                mutableLiveData.value = SiteInfo(
                    id = it.id,
                    url = it.url,
                    name = it.name,
                    password = AESCrypt.decrypt(it.password)
                )
            }
        }
        return mutableLiveData
    }

    suspend fun saveSiteSettings(siteInfo: SiteInfoEditState) {
        db.siteInfoDao().saveSiteSettings(
            id = siteInfo.id,
            url =siteInfo.url,
            password = AESCrypt.encrypt(siteInfo.password),
            siteName = siteInfo.name
        )
    }

    suspend fun saveNewSiteSettings(siteInfo: IconItemAddState) {
        db.siteInfoDao().saveNewSiteSettings(
           SiteInfo(
               id = 0,
               url = siteInfo.url,
               name = siteInfo.name,
               password = AESCrypt.encrypt(siteInfo.password),
           )
        )
    }
}
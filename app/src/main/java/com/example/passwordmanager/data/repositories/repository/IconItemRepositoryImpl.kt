package com.example.passwordmanager.data.repositories.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.passwordmanager.data.Room.Model.SiteInfo
import com.example.passwordmanager.data.Room.SiteInfoDatabase
import javax.inject.Inject

class IconItemRepositoryImpl @Inject constructor(
    private val db: SiteInfoDatabase
) {
    fun getSiteInfo(name:String): LiveData<SiteInfo>{
        Log.e("GET", "GET")
        return db.siteInfoDao().getSiteDetails(name)
    }
}
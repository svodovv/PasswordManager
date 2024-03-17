package com.example.passwordmanager.data.Room.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.passwordmanager.data.Room.Model.SiteInfo

@Dao
interface SiteInfoDao {

    @Query("SELECT * FROM siteinfo")
    fun getUrlIcons(): LiveData<List<SiteInfo>>

    @Query("SELECT * FROM siteinfo WHERE name = :name")
    fun getSiteDetails(name: String): LiveData<SiteInfo>

    @Delete
    suspend fun deleteIconById(siteInfo: SiteInfo): Int

    @Query("INSERT OR REPLACE INTO siteinfo (id, url, password, name) VALUES (:id, :url, :password, :siteName)")
    suspend fun saveSiteSettings(id: Int, url: String, password: String, siteName: String)


    @Insert
    suspend fun saveNewSiteSettings(siteInfo: SiteInfo)
}


package com.example.passwordmanager.data.Room.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import com.example.passwordmanager.data.Room.Model.SiteInfo
import org.jetbrains.annotations.NotNull

@Dao
interface SiteInfoDao {

    @Query("SELECT * FROM siteinfo")
    fun getUrlIcons(): LiveData<List<SiteInfo>>

    @Query("SELECT * FROM siteinfo WHERE name = :name")
    fun getSiteDetails(name: String): LiveData<SiteInfo>

    @Query("DELETE FROM siteinfo WHERE name = :name")
    suspend fun deleteIconByName( name: String): Int


}


package com.example.passwordmanager.data.Room

import AESCrypt
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.RoomSQLiteQuery
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.passwordmanager.data.Room.Dao.MasterPasswordDao
import com.example.passwordmanager.data.Room.Dao.SiteInfoDao
import com.example.passwordmanager.data.Room.Dao.UserAuthenticatedDao
import com.example.passwordmanager.data.Room.Model.MasterPassword
import com.example.passwordmanager.data.Room.Model.SiteInfo
import com.example.passwordmanager.data.Room.Model.UserAuthenticated

@Database(
    entities = [SiteInfo::class, MasterPassword::class, UserAuthenticated::class],
    version = 1,
)
abstract class SiteInfoDatabase: RoomDatabase(){

    abstract fun materPasswordDao() : MasterPasswordDao

    abstract fun siteInfoDao(): SiteInfoDao

    abstract fun userAuth(): UserAuthenticatedDao

    companion object{
        fun getSiteInfoDatabase(context: Context): SiteInfoDatabase{
            return Room.databaseBuilder(
                context = context,
                SiteInfoDatabase::class.java,
                "PasswordManager"
            )
                .addCallback(object : Callback(){
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        val userAuthenticated = UserAuthenticated(1, false)
                        val sitesInfo = listOf(
                            SiteInfo(
                                id = 0,
                                url = "https://cdn-icons-png.flaticon.com/512/4494/4494517.png",
                                name = "vk",
                                password = AESCrypt.encrypt("1asdas23asdasd")
                            ),
                            SiteInfo(
                                id = 1,
                                url = "https://cdn-icons-png.flaticon.com/512/3670/3670250.png",
                                name = "ok",
                                password =  AESCrypt.encrypt("asd1sdfafa2as3")
                            ),
                            SiteInfo(
                                id = 2,
                                url = "https://cdn-icons-png.flaticon.com/512/2111/2111646.png",
                                name = "telegram",
                                password =  AESCrypt.encrypt("1asd2fasd3")
                            ),
                            SiteInfo(
                                id = 3,
                                url = "https://cdn-icons-png.flaticon.com/512/4494/4494475.png",
                                name = "facebook",
                                password =  AESCrypt.encrypt("123asddasda")
                            ),
                            SiteInfo(
                                id = 4,
                                url = "https://cdn-icons-png.flaticon.com/512/4494/4494477.png",
                                name = "twitter",
                                password =  AESCrypt.encrypt("1asdasdasd23")
                            ),
                            SiteInfo(
                                id = 5,
                                url = "https://cdn-icons-png.flaticon.com/512/733/733585.png",
                                name = "whatsApp",
                                password =  AESCrypt.encrypt("12asd3fasda")
                            ),
                        )
                        sitesInfo.forEach {siteInfo ->
                            db.insert("SiteInfo", SQLiteDatabase.CONFLICT_ROLLBACK, siteInfo.toContentValues() )
                        }
                        db.insert("UserAuth",  SQLiteDatabase.CONFLICT_ROLLBACK, userAuthenticated.toContentValues())
                    }
                })
                .build()
        }
    }
}
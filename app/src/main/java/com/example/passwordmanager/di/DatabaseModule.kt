package com.example.passwordmanager.di

import android.content.Context
import com.example.passwordmanager.data.Room.SiteInfoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): SiteInfoDatabase {
        return SiteInfoDatabase.getSiteInfoDatabase(context)
    }

}
package com.example.passwordmanager

import android.app.Application
import android.transition.Transition
import androidx.compose.material3.CircularProgressIndicator
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.util.DebugLogger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication: Application(), ImageLoaderFactory  {
    override fun newImageLoader(): ImageLoader {
        return ImageLoader(this).newBuilder()
            .error(R.drawable.baseline_error_90)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .memoryCache{
                MemoryCache.Builder(this)
                    .maxSizePercent(0.1)
                    .strongReferencesEnabled(true)
                    .build()
            }
            .diskCachePolicy(CachePolicy.ENABLED)
            .diskCache {
                DiskCache.Builder()
                    .maxSizePercent(0.03)
                    .directory(cacheDir)
                    .build()
            }
            .logger(DebugLogger())
            .build()
    }
}
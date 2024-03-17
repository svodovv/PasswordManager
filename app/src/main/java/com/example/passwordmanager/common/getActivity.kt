package com.example.passwordmanager.common

import android.content.Context
import android.content.ContextWrapper
import androidx.fragment.app.FragmentActivity

inline fun <reified Activity : FragmentActivity> Context.getActivity(): Activity? {
    return when (this) {
        is Activity -> this
        else -> {
            var context = this
            while (context is ContextWrapper) {
                context = context.baseContext
                if (context is Activity) return context
            }
            null
        }
    }
}
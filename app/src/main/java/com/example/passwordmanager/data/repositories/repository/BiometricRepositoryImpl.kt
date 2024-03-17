package com.example.passwordmanager.data.repositories.repository

import android.content.Context
import android.util.Log
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_WEAK
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class BiometricRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context

) {

    fun authenticateUser(): Boolean {
        val biometricManager = BiometricManager.from(context)

         when (biometricManager.canAuthenticate(BIOMETRIC_WEAK)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                Log.e("BiometricManager", "true")
                return true
            }
             BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                 // No biometric in deivice
                 Log.e("BiometricManager", "BIOMETRIC_ERROR_NO_HARDWARE")
                 return false
             }

             BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                 Log.e("BiometricManager", "BIOMETRIC_ERROR_HW_UNAVAILABLE")
                 return false
             }

             BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED -> {
                 // Biometric features available but a security vulnerability has been discovered
                 Log.e("BiometricManager", "BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED")
                 return false
             }

             BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED -> {
                 // Biometric features are currently unavailable because the specified options are incompatible with the current Android version..
                 Log.e("BiometricManager", "BIOMETRIC_ERROR_UNSUPPORTED")
                 return false
             }

             BiometricManager.BIOMETRIC_STATUS_UNKNOWN -> {
                 // Unable to determine whether the user can authenticate using biometrics
                 Log.e("BiometricManager", "BIOMETRIC_STATUS_UNKNOWN")
                 return false
             }

             BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                 // The user can't authenticate because no biometric or device credential is enrolled.
                 Log.e("BiometricManager", "BIOMETRIC_ERROR_NONE_ENROLLED")
                 return false
             }
             else -> {
                 Log.e("BiometricManager", "else")
                 return false
             }

        }
    }

    val promptInfo = BiometricPrompt.PromptInfo.Builder()
        .setTitle("AndroidX Biometric")
        .setSubtitle("Authenticate user via Biometric")
        .setDescription("Please authenticate yourself here")
        .setAllowedAuthenticators(BIOMETRIC_WEAK)
        .setNegativeButtonText("Cancel")
        .setConfirmationRequired(false)
        .build()


    fun getBiometricPrompt(authCallback: BiometricPrompt.AuthenticationCallback, activity: FragmentActivity) =
        BiometricPrompt(activity, ContextCompat.getMainExecutor(context), authCallback)

}
package com.example.passwordmanager.ui.screens.IconItemAdd

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passwordmanager.data.repositories.repository.IconItemRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IconItemAddViewModel @Inject constructor(
    private val repo: IconItemRepositoryImpl
) : ViewModel() {

    private val _siteInfo = mutableStateOf(IconItemAddState())
    val siteInfo: State<IconItemAddState> = _siteInfo


    fun saveNewSiteSettings() {
        viewModelScope.launch {
            repo.saveNewSiteSettings(siteInfo.value)
        }
    }

    fun onChangeUrl(url: String) {
        _siteInfo.value = _siteInfo.value.copy(
            url = url
        )
    }

    fun onChangePassword(password: String) {
        _siteInfo.value = _siteInfo.value.copy(
            password = password
        )
    }

    fun onChangeSiteName(siteName: String) {
        _siteInfo.value = _siteInfo.value.copy(
            name = siteName
        )
    }
}
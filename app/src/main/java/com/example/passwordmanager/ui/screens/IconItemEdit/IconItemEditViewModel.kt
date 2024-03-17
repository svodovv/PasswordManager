package com.example.passwordmanager.ui.screens.IconItemEdit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passwordmanager.data.repositories.repository.IconItemRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IconItemEditViewModel @Inject constructor(
    private val repo: IconItemRepositoryImpl
) : ViewModel() {

    private val _siteInfo = mutableStateOf(SiteInfoEditState())
    val siteInfo: State<SiteInfoEditState> = _siteInfo

    fun getSiteInfo(name: String?) {
        name?.let {
            repo.getSiteInfo(name).observeForever { siteInfo ->
                siteInfo.let {
                    if (it != null) {
                        _siteInfo.value = _siteInfo.value.copy(
                            id = it.id,
                            url = it.url,
                            name = it.name,
                            password = it.password,
                        )
                    }
                }
            }
        }
    }

    fun saveSiteSettings() {
        viewModelScope.launch {
            repo.saveSiteSettings(siteInfo.value)
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